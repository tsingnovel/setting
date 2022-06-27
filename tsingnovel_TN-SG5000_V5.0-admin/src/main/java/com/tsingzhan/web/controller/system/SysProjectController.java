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
import com.tsingzhan.system.domain.SysProject;
import com.tsingzhan.system.service.ISysProjectService;
import com.tsingzhan.common.utils.poi.ExcelUtil;
import com.tsingzhan.common.core.page.TableDataInfo;

/**
 * 孵化Controller
 *
 * @author ruoyi
 * @date 2021-02-24
 */
@RestController
@RequestMapping("/system/project")
public class SysProjectController extends BaseController
{
    @Autowired
    private ISysProjectService sysProjectService;

    /**
     * 查询孵化列表
     */
    @PreAuthorize("@ss.hasPermi('system:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysProject sysProject)
    {
        startPage();
        List<SysProject> list = sysProjectService.selectSysProjectList(sysProject);
        return getDataTable(list);
    }

    /**
     * 导出孵化列表
     */
    @PreAuthorize("@ss.hasPermi('system:project:export')")
    @Log(title = "孵化", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysProject sysProject)
    {
        List<SysProject> list = sysProjectService.selectSysProjectList(sysProject);
        ExcelUtil<SysProject> util = new ExcelUtil<SysProject>(SysProject.class);
        return util.exportExcel(list, "project");
    }

    /**
     * 获取孵化详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:project:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Integer projectId)
    {
        return AjaxResult.success(sysProjectService.selectSysProjectById(projectId));
    }

    /**
     * 新增孵化
     */
    @PreAuthorize("@ss.hasPermi('system:project:add')")
    @Log(title = "孵化", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysProject sysProject)
    {
        return toAjax(sysProjectService.insertSysProject(sysProject));
    }

    /**
     * 修改孵化
     */
    @PreAuthorize("@ss.hasPermi('system:project:edit')")
    @Log(title = "孵化", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysProject sysProject)
    {
        return toAjax(sysProjectService.updateSysProject(sysProject));
    }

    /**
     * 删除孵化
     */
    @PreAuthorize("@ss.hasPermi('system:project:remove')")
    @Log(title = "孵化", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Integer[] projectIds)
    {
        return toAjax(sysProjectService.deleteSysProjectByIds(projectIds));
    }
}
