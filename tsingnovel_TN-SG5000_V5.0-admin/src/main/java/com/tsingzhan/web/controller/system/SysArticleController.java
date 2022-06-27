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
import com.tsingzhan.system.domain.SysArticle;
import com.tsingzhan.system.service.ISysArticleService;
import com.tsingzhan.common.utils.poi.ExcelUtil;
import com.tsingzhan.common.core.page.TableDataInfo;

/**
 * 文章Controller
 *
 * @author pankai
 * @date 2021-02-25
 */
@RestController
@RequestMapping("/system/article")
public class SysArticleController extends BaseController
{
    @Autowired
    private ISysArticleService sysArticleService;

    /**
     * 查询文章列表
     */
    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysArticle sysArticle)
    {
        startPage();
        List<SysArticle> list = sysArticleService.selectSysArticleList(sysArticle);
        return getDataTable(list);
    }

    /**
     * 导出文章列表
     */
    @PreAuthorize("@ss.hasPermi('system:article:export')")
    @Log(title = "文章", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysArticle sysArticle)
    {
        List<SysArticle> list = sysArticleService.selectSysArticleList(sysArticle);
        ExcelUtil<SysArticle> util = new ExcelUtil<SysArticle>(SysArticle.class);
        return util.exportExcel(list, "article");
    }

    /**
     * 获取文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:article:query')")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Integer articleId)
    {
        return AjaxResult.success(sysArticleService.selectSysArticleById(articleId));
    }

    /**
     * 新增文章
     */
    @PreAuthorize("@ss.hasPermi('system:article:add')")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysArticle sysArticle)
    {
        return toAjax(sysArticleService.insertSysArticle(sysArticle));
    }

    /**
     * 修改文章
     */
    @PreAuthorize("@ss.hasPermi('system:article:edit')")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysArticle sysArticle)
    {
        return toAjax(sysArticleService.updateSysArticle(sysArticle));
    }

    /**
     * 删除文章
     */
    @PreAuthorize("@ss.hasPermi('system:article:remove')")
    @Log(title = "文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Integer[] articleIds)
    {
        return toAjax(sysArticleService.deleteSysArticleByIds(articleIds));
    }
}
