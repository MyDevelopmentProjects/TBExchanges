package ge.mgl.controller;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import ge.mgl.entities.TCCY;
import ge.mgl.service.TCCYService;
import ge.mgl.utils.GeneralUtil;
import ge.mgl.utils.RequestResponse;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.net.URL;
import java.util.*;

import static ge.mgl.utils.constants.Constants.ControllerCodes.*;

@Controller
@RequestMapping("/ccy")
public class TCCYController {

    @Autowired
    private TCCYService service;

    String[] ccyList = { "EUR", "GBP", "USD" };

    @RequestMapping(value = "/rss", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String[]> getFeedInRss() {
        String url = "http://www.nbg.ge/rss.php";
        Map<String, String[]> trtd = new HashMap<>(3);
        try {
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
            if (feed != null) {
                if (feed.getEntries() != null && feed.getEntries().size() > 0 && feed.getEntries().get(0).getDescription() != null) {
                    Document doc = Jsoup.parse(feed.getEntries().get(0).getDescription().toString());
                    Elements tables = doc.select("table");
                    for (Element table : tables) {
                        Elements trs = table.select("tr");
                        for (int i = 0; i < trs.size(); i++) {
                            Elements tds = trs.get(i).select("td");
                            if (Arrays.asList(ccyList).contains(tds.get(0).text())) {
                                String[] values = new String[2];
                                values[0] = tds.get(2).text();
                                values[1] = tds.get(4).text();
                                trtd.put(tds.get(0).text(), values);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trtd;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PaginationAndFullSearchQueryResult getList(
            @RequestParam(required = false, defaultValue = STRING_EMPTY) String searchExpression,
            @RequestParam(required = false, defaultValue = STRING_EMPTY) String sortField,
            @RequestParam(required = false, defaultValue = IS_ASCENDING_DEFAULT_VALUE) boolean isAscending,
            @RequestParam(value = PAGE_NUMBER, required = false, defaultValue = PAGE_NUMBER_DEFAULT_VALUE) Integer pageNumber,
            @RequestParam(required = false, defaultValue = PAGE_SIZE_DEFAULT_VALUE) int pageSize) {
        return service.getList(searchExpression, sortField, isAscending, pageNumber, pageSize);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + PUT, method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse save(@RequestBody TCCY tccy) {
        service.save(tccy);
        return GeneralUtil.RequestSuccess();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + DELETE, method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse delete(@RequestBody Long id) {
        if (service.delete(id)) {
            return GeneralUtil.RequestSuccess();
        }
        return GeneralUtil.RequestError();
    }

}
