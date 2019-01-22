package ge.mgl.controller;

import ge.mgl.entities.TCCYExchange;
import ge.mgl.service.TCCYExchangeService;
import ge.mgl.utils.GeneralUtil;
import ge.mgl.utils.RequestResponse;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static ge.mgl.utils.constants.Constants.ControllerCodes.*;

@Controller
@RequestMapping("/ccyexchange")
public class TCCYExchangeController {

    @Autowired
    private TCCYExchangeService ccyexchangeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + LIST, method = RequestMethod.GET)
    @ResponseBody
    public PaginationAndFullSearchQueryResult getList(
            @RequestParam(required = false, defaultValue = STRING_EMPTY) String searchExpression,
            @RequestParam(required = false, defaultValue = STRING_EMPTY) String sortField,
            @RequestParam(required = false, defaultValue = IS_ASCENDING_DEFAULT_VALUE) boolean isAscending,
            @RequestParam(value = PAGE_NUMBER, required = false, defaultValue = PAGE_NUMBER_DEFAULT_VALUE) Integer pageNumber,
            @RequestParam(required = false, defaultValue = PAGE_SIZE_DEFAULT_VALUE) int pageSize,
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String clientId,
            @RequestParam(required = false) Long ccyFrom,
            @RequestParam(required = false) Long ccyTo) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Date startDate = null;
            Date endDate = null;

            if (dateFrom != null && dateTo != null && dateFrom.length() > 0 && dateTo.length() > 0) {
                startDate = GeneralUtil.asDate(LocalDate.parse(dateFrom, formatter));
                endDate = GeneralUtil.asDate(LocalDate.parse(dateTo, formatter));
            }


        return ccyexchangeService.getList(searchExpression, sortField, isAscending, pageNumber, pageSize,
                startDate,
                endDate,
                userId,
                clientId,
                ccyFrom,
                ccyTo);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + PUT, method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse save(@RequestBody TCCYExchange ccyexchange) {
        ccyexchangeService.save(ccyexchange);
        return GeneralUtil.RequestSuccess();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + DELETE, method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse delete(@RequestBody Long id) {
        boolean deleted = ccyexchangeService.delete(id);
        if (deleted) {
            return GeneralUtil.RequestSuccess();
        }
        return GeneralUtil.RequestError();
    }
}