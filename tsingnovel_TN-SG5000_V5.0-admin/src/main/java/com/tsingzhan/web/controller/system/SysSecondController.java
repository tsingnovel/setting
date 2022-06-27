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
import com.tsingzhan.system.domain.SysSecond;
import com.tsingzhan.system.service.ISysSecondService;
import com.tsingzhan.common.utils.poi.ExcelUtil;
import com.tsingzhan.common.core.page.TableDataInfo;

/**
 * 文章分类Controller
 *
 * @author ruoyi
 * @date 2021-02-24
 */
@RestController
@RequestMapping("/system/second")
public class SysSecondController extends BaseController
{
    @Autowired
    private ISysSecondService sysSecondService;

    /**
     * 查询文章分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:second:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysSecond sysSecond)
    {
        startPage();
        List<SysSecond> list = sysSecondService.selectSysSecondList(sysSecond);
        return getDataTable(list);
    }

    /**
     * 导出文章分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:second:export')")
    @Log(title = "文章分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysSecond sysSecond)
    {
        List<SysSecond> list = sysSecondService.selectSysSecondList(sysSecond);
        ExcelUtil<SysSecond> util = new ExcelUtil<SysSecond>(SysSecond.class);
        return util.exportExcel(list, "second");
    }

    /**
     * 获取文章分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:second:query')")
    @GetMapping(value = "/{secondId}")
    public AjaxResult getInfo(@PathVariable("secondId") Integer secondId)
    {
        return AjaxResult.success(sysSecondService.selectSysSecondById(secondId));
    }

    /**
     * 新增文章分类
     */
    @PreAuthorize("@ss.hasPermi('system:second:add')")
    @Log(title = "文章分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSecond sysSecond)
    {
        return toAjax(sysSecondService.insertSysSecond(sysSecond));
    }

    /**
     * 修改文章分类
     */
    @PreAuthorize("@ss.hasPermi('system:second:edit')")
    @Log(title = "文章分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSecond sysSecond)
    {
        return toAjax(sysSecondService.updateSysSecond(sysSecond));
    }

    /**
     * 删除文章分类
     */
    @PreAuthorize("@ss.hasPermi('system:second:remove')")
    @Log(title = "文章分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{secondIds}")
    public AjaxResult remove(@PathVariable Integer[] secondIds)
    {
        return toAjax(sysSecondService.deleteSysSecondByIds(secondIds));
    }
}
