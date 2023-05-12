package start17.Memento.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import start17.Memento.domain.search.CaseContent;
import start17.Memento.domain.search.CaseInfo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class SearchService {

    public CaseContent getCaseContent(int num) throws IOException{
        String url = "https://www.law.go.kr/DRF/lawService.do?OC=yoonsoo98&target=prec&ID="+Integer.toString(num)+ "&type=XML";

        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            //페이지에 접근할 Document 객체 생성 -> url 읽어들임
            Document doc = dBuilder.parse(url);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("PrecService");

            Element eElement = (Element)nList.item(0);
            CaseContent cc = CaseContent.builder()
                    .title(getTagValue("사건명",eElement))
                    .main(getTagValue("판결요지",eElement))
                    .provision(getTagValue("참조조문",eElement))
                    .sentence(getTagValue("판시사항",eElement))
                    .reason(getTagValue("판례내용",eElement))
                    .build();
            return cc;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

    }
    public List<CaseInfo> getCasesListbyQuery(String query) throws IOException {
        String url = "http://www.law.go.kr/DRF/lawSearch.do?OC=yoonsoo98&target=prec&type=XML&query=" + query;
        return getCasesList(url);
    }


    public List<CaseInfo> getCasesList(String url) throws IOException{
        List<CaseInfo> caseInfoList = new ArrayList<>();
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            //페이지에 접근할 Document 객체 생성 -> url 읽어들임
            Document doc = dBuilder.parse(url);

            doc.getDocumentElement().normalize();

            NodeList count = doc.getElementsByTagName("PrecSearch");

            int totalCnt = Integer.parseInt(getTagValue("totalCnt", (Element) count.item(0)));
            if (totalCnt != 0){
                NodeList nList = doc.getElementsByTagName("prec");

                for (int page = 0; page <= totalCnt/20;){
                    for(int temp = 0; temp<nList.getLength(); temp++) {
                        Node nNode = nList.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            CaseInfo caseInfo = CaseInfo.builder()
                                    .number(Integer.parseInt(getTagValue("판례일련번호", eElement)))
                                    .name(getTagValue("사건명", eElement))
                                    .casenum(getTagValue("사건번호", eElement))
                                    .court(getTagValue("법원명", eElement))
                                    .type(getTagValue("사건종류명", eElement))
                                    .judge_type(getTagValue("판결유형", eElement))
                                    .url(getTagValue("판례상세링크", eElement))
                                    .build();
                            caseInfoList.add(caseInfo);
                        }
                        page++;
                        String pageUrl = url + "&page=" + page;
                        doc = dBuilder.parse(pageUrl);
                        doc.getDocumentElement().normalize();
                    }
                }
            }
            else {
                caseInfoList = Collections.emptyList();
                return caseInfoList;
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
        if(nValue==null) return "";
        return nValue.getNodeValue();
    }


}

