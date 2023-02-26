package start17.Memento.service.search;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import start17.Memento.domain.search.CaseInfo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SearchWithQueryService {

    public List<CaseInfo> getCasesList(String query) throws IOException{
        String url = "http://www.law.go.kr/DRF/lawSearch.do?OC=yoonsoo98&target=prec&type=XML&query=";
        List<CaseInfo> caseInfoList = new ArrayList<>();
        try{
            url = url + query;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            //페이지에 접근할 Document 객체 생성 -> url 읽어들임
            Document doc = dBuilder.parse(url);

            doc.getDocumentElement().normalize();

            NodeList count = doc.getElementsByTagName("PrecSearch");

            int totalCnt = Integer.parseInt(getTagValue("totalCnt", (Element) count.item(0)));

            NodeList nList = doc.getElementsByTagName("prec");

            for (int page = 0; page < totalCnt/20;){
                for(int temp = 0; temp<nList.getLength(); temp++){
                    Node nNode = nList.item(temp);
                    if(nNode.getNodeType() == Node.ELEMENT_NODE){
                        Element eElement = (Element) nNode;
                        CaseInfo caseInfo = CaseInfo.builder()
                                .number(Integer.parseInt(getTagValue("판례일련번호",eElement)))
                                .name(getTagValue("사건명",eElement))
                                .casenum(getTagValue("사건번호",eElement))
                                .court(getTagValue("법원명",eElement))
                                .type(getTagValue("사건종류명",eElement))
                                .judge_type(getTagValue("판결유형",eElement))
                                .url(getTagValue("판례상세링크",eElement))
                                .build();
                        caseInfoList.add(caseInfo);
                    }
                    page++;
                    url="https://www.law.go.kr/DRF/lawSearch.do?OC=yoonsoo98&target=prec&type=XML&query=특허&page="+page;
                    doc = dBuilder.parse(url);
                    doc.getDocumentElement().normalize();

                }
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        return caseInfoList;
    }

    private static String getTagValue(String tag, Element eElement) {
        Node nValue=null;

        NodeList x= eElement.getElementsByTagName(tag);
        Node test=x.item(0);
        NodeList t=null;
        if(test!=null) {
            t= test.getChildNodes();
            if((Node)t.item(0)!=null) {nValue=(Node)t.item(0);}
        }
        if(nValue==null) return null;
        return nValue.getNodeValue();
    }

}

