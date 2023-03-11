package start17.Memento.service.search;

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
import java.util.List;


@Service
public class SearchResultShowService {

    public CaseContent getCaseContent() throws IOException{
        String url = "https://www.law.go.kr/DRF/lawService.do?OC=yoonsoo98&target=prec&ID=228541&type=XML";

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

