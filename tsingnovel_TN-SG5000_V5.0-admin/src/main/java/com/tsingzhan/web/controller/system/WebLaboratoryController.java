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
import com.tsingzhan.system.domain.WebLaboratory;
import com.tsingzhan.system.service.IWebLaboratoryService;
import com.tsingzhan.common.utils.poi.ExcelUtil;
import com.tsingzhan.common.core.page.TableDataInfo;

/**
 * 线上实验室Controller
 *
 * @author ruoyi
 * @date 2021-04-29
 */
@RestController
@RequestMapping("/system/laboratory")
public class WebLaboratoryController extends BaseController
{
    @Autowired
    private IWebLaboratoryService webLaboratoryService;

    /**
     * 查询线上实验室列表
     */
    @PreAuthorize("@ss.hasPermi('system:laboratory:list')")
    @GetMapping("/list")
    public TableDataInfo list(WebLaboratory webLaboratory)
    {
        startPage();
        List<WebLaboratory> list = webLaboratoryService.selectWebLaboratoryList(webLaboratory);
        return getDataTable(list);
    }

    /**
     * 导出线上实验室列表
     */
    @PreAuthorize("@ss.hasPermi('system:laboratory:export')")
    @Log(title = "线上实验室", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WebLaboratory webLaboratory)
    {
        List<WebLaboratory> list = webLaboratoryService.selectWebLaboratoryList(webLaboratory);
        ExcelUtil<WebLaboratory> util = new ExcelUtil<WebLaboratory>(WebLaboratory.class);
        return util.exportExcel(list, "laboratory");
    }

    /**
     * 获取线上实验室详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:laboratory:query')")
    @GetMapping(value = "/{laboratoryId}")
    public AjaxResult getInfo(@PathVariable("laboratoryId") Integer laboratoryId)
    {
        return AjaxResult.success(webLaboratoryService.selectWebLaboratoryById(laboratoryId));
    }

    /**
     * 新增线上实验室
     */
    @PreAuthorize("@ss.hasPermi('system:laboratory:add')")
    @Log(title = "线上实验室", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WebLaboratory webLaboratory)
    {
        return toAjax(webLaboratoryService.insertWebLaboratory(webLaboratory));
    }

    /**
     * 修改线上实验室
     */
    @PreAuthorize("@ss.hasPermi('system:laboratory:edit')")
    @Log(title = "线上实验室", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WebLaboratory webLaboratory)
    {
        return toAjax(webLaboratoryService.updateWebLaboratory(webLaboratory));
    }

    /**
     * 删除线上实验室
     */
    @PreAuthorize("@ss.hasPermi('system:laboratory:remove')")
    @Log(title = "线上实验室", businessType = BusinessType.DELETE)
	@DeleteMapping("/{laboratoryIds}")
    public AjaxResult remove(@PathVariable Integer[] laboratoryIds)
    {
        return toAjax(webLaboratoryService.deleteWebLaboratoryByIds(laboratoryIds));
    }
}
