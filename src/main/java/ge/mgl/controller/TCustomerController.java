package ge.mgl.controller;

import ge.mgl.entities.TCustomer;
import ge.mgl.service.TCustomerService;
import ge.mgl.utils.GeneralUtil;
import ge.mgl.utils.RequestResponse;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static ge.mgl.utils.constants.Constants.ControllerCodes.*;

@Controller
@RequestMapping("/customer")
public class TCustomerController {
    @Autowired
    private TCustomerService customerService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + LIST, method = RequestMethod.GET)
    @ResponseBody
    public PaginationAndFullSearchQueryResult getList(@RequestParam(required = false, defaultValue = STRING_EMPTY) String searchExpression,
                                                      @RequestParam(required = false, defaultValue = STRING_EMPTY) String sortField,
                                                      @RequestParam(required = false, defaultValue = IS_ASCENDING_DEFAULT_VALUE) boolean isAscending,
                                                      @RequestParam(value = PAGE_NUMBER, required = false, defaultValue = PAGE_NUMBER_DEFAULT_VALUE) Integer pageNumber,
                                                      @RequestParam(required = false, defaultValue = PAGE_SIZE_DEFAULT_VALUE) int pageSize) {
        return customerService.getList(searchExpression, sortField, isAscending, pageNumber, pageSize);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + PUT, method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse save(@RequestBody TCustomer customer) {
        customerService.save(customer);
        return GeneralUtil.RequestSuccess();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + DELETE, method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse delete(@RequestBody Long id) {
        boolean deleted = customerService.delete(id);
        if (deleted) {
            return GeneralUtil.RequestSuccess();
        }
        return GeneralUtil.RequestError();
    }
}