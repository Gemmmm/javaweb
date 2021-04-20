package ${packageName}.controller;

import com.javaweb.common.framework.common.BaseController;
import com.javaweb.common.framework.utils.JsonResult;
import ${packageName}.entity.${entityName};
import ${packageName}.query.${entityName}Query;
import ${packageName}.service.I${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * ${tableAnnotation} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
@RequestMapping("/${entityName?lower_case}")
public class ${entityName}Controller extends BaseController {

    @Autowired
    private I${entityName}Service ${entityName?uncap_first}Service;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/index")
    public JsonResult index(@RequestBody ${entityName}Query query) {
        return ${entityName?uncap_first}Service.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody ${entityName} entity) {
        return ${entityName?uncap_first}Service.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param ${entityName?lower_case}Id 记录ID
     * @return
     */
    @GetMapping("/info/{${entityName?lower_case}Id}")
    public JsonResult info(@PathVariable("levelId") Integer ${entityName?lower_case}Id) {
        return ${entityName?uncap_first}Service.info(${entityName?lower_case}Id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody ${entityName} entity) {
        return ${entityName?uncap_first}Service.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param ${entityName?lower_case}Ids 记录ID
     * @return
     */
    @GetMapping("/delete/{${entityName?lower_case}Ids}")
    public JsonResult delete(@PathVariable("${entityName?lower_case}Ids") String ${entityName?lower_case}Ids) {
        return ${entityName?uncap_first}Service.deleteByIds(${entityName?lower_case}Ids);
    }
}