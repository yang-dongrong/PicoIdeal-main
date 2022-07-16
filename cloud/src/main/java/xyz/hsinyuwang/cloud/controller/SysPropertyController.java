package xyz.hsinyuwang.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.hsinyuwang.cloud.domain.AjaxResult;
import xyz.hsinyuwang.cloud.domain.entity.SysPropType;
import xyz.hsinyuwang.cloud.domain.entity.SysProperty;
import xyz.hsinyuwang.cloud.page.TableDataInfo;
import xyz.hsinyuwang.cloud.service.ISysPropTypeService;
import xyz.hsinyuwang.cloud.service.ISysPropertyService;
import xyz.hsinyuwang.cloud.utils.StringUtils;

/**
 * 属性Controller
 *
 * @author hsinyuwang
 * @date 2022-05-28
 */
@RestController
@RequestMapping("/property")
public class SysPropertyController extends BaseController {
    @Autowired
    private ISysPropertyService sysPropertyService;

    @Autowired
    private ISysPropTypeService sysPropTypeService;

    /**
     * 查询属性列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysProperty sysProperty) {
        startPage();
        List<SysProperty> list = sysPropertyService.selectSysPropertyList(sysProperty);
        return getDataTable(list);
    }

    /**
     * 获取属性详细信息
     */
    @GetMapping(value = {"/", "/{propertyId}"})
    public AjaxResult getInfo(@PathVariable(value = "propertyId", required = false) Long propertyId) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("propTypes", sysPropTypeService.selectSysPropTypeList(new SysPropType()));
        if (StringUtils.isNotNull(propertyId)) {
            ajax.put(AjaxResult.DATA_TAG, sysPropertyService.selectSysPropertyByPropertyId(propertyId));
        }
        return ajax;
    }

    /**
     * 新增属性
     */
    @PostMapping
    public AjaxResult add(@RequestBody SysProperty sysProperty) {
        return toAjax(sysPropertyService.insertSysProperty(sysProperty));
    }

    /**
     * 修改属性
     */
    @PutMapping
    public AjaxResult edit(@RequestBody SysProperty sysProperty) {
        return toAjax(sysPropertyService.updateSysProperty(sysProperty));
    }

    /**
     * 删除属性
     */
    @DeleteMapping("/{propertyIds}")
    public AjaxResult remove(@PathVariable Long[] propertyIds) {
        return toAjax(sysPropertyService.deleteSysPropertyByPropertyIds(propertyIds));
    }
}
