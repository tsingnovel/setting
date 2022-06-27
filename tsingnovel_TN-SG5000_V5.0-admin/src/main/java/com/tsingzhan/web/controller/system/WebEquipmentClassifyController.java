package com.tsingzhan.web.controller.system;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tsingzhan.common.annotation.Log;
import com.tsingzhan.common.core.controller.BaseController;
import com.tsingzhan.common.core.domain.AjaxResult;
import com.tsingzhan.common.enums.BusinessType;
import com.tsingzhan.system.domain.WebEquipmentClassify;
import com.tsingzhan.system.service.IWebEquipmentClassifyService;
import com.tsingzhan.common.utils.poi.ExcelUtil;
import com.tsingzhan.common.core.page.TableDataInfo;

/**
 * 线上实验室设备分类Controller
 *
 * @author ruoyi
 * @date 2021-04-29
 */
@RestController
@RequestMapping("/system/classify")
public class WebEquipmentClassifyController extends BaseController
{
    @Autowired
    private IWebEquipmentClassifyService webEquipmentClassifyService;

    /**
     * 查询线上实验室设备分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:classify:list')")
    @GetMapping("/list")
    public TableDataInfo list(WebEquipmentClassify webEquipmentClassify)
    {
        startPage();
        List<WebEquipmentClassify> list = webEquipmentClassifyService.selectWebEquipmentClassifyList(webEquipmentClassify);
        return getDataTable(list);
    }

    /**
     * 导出线上实验室设备分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:classify:export')")
    @Log(title = "线上实验室设备分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WebEquipmentClassify webEquipmentClassify)
    {
        List<WebEquipmentClassify> list = webEquipmentClassifyService.selectWebEquipmentClassifyList(webEquipmentClassify);
        ExcelUtil<WebEquipmentClassify> util = new ExcelUtil<WebEquipmentClassify>(WebEquipmentClassify.class);
        return util.exportExcel(list, "classify");
    }

    /**
     * 获取线上实验室设备分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:classify:query')")
    @GetMapping(value = "/{equipmentClassifyId}")
    public AjaxResult getInfo(@PathVariable("equipmentClassifyId") Integer equipmentClassifyId)
    {
        return AjaxResult.success(webEquipmentClassifyService.selectWebEquipmentClassifyById(equipmentClassifyId));
    }

    /**
     * 新增线上实验室设备分类
     */
    @PreAuthorize("@ss.hasPermi('system:classify:add')")
    @Log(title = "线上实验室设备分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WebEquipmentClassify webEquipmentClassify)
    {
        return toAjax(webEquipmentClassifyService.insertWebEquipmentClassify(webEquipmentClassify));
    }

    /**
     * 修改线上实验室设备分类
     */
    @PreAuthorize("@ss.hasPermi('system:classify:edit')")
    @Log(title = "线上实验室设备分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WebEquipmentClassify webEquipmentClassify)
    {
        return toAjax(webEquipmentClassifyService.updateWebEquipmentClassify(webEquipmentClassify));
    }

    /**
     * 删除线上实验室设备分类
     */
    @PreAuthorize("@ss.hasPermi('system:classify:remove')")
    @Log(title = "线上实验室设备分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{equipmentClassifyIds}")
    public AjaxResult remove(@PathVariable Integer[] equipmentClassifyIds)
    {
        return toAjax(webEquipmentClassifyService.deleteWebEquipmentClassifyByIds(equipmentClassifyIds));
    }
}
