# spring 注解全家桶(记忆)
>3条主线（满足百分80的需求）
## 主线一：组件进站（把类扔到spring容器中）
>“把类扔到spring容器中”的解释：让Spring扫到这个类；让Spring实例化并保管起来（成为Bean），后面用@Autowired使用它。

@Controller / @Restcontroller <-Web层（这里有个补充的知识点[^补充的知识点]）
@Service <-业务层
@Repository  <-DAO层（myBatis省略）
@Component   <-以上都不适用的时候兜底

[^补充的知识点]: @Restcontroller=@Controller+@ResponseBody。当要返回json格式的文件给前端的时候，单单使用@Controller是不够的，还要追加@ResponceBody。所以我建议使用@RestController

## 主线二：请求进站（把 HTTP 流量接进来）
┌─ 查              @GetMapping("/list")
├─ 增              @PostMapping
├─ 改              @PutMapping
└─ 删              @DeleteMapping
## 主线三：参数出站（把Http数据注入到方法中）
┌─ 路径参数        /user/{id}     ┐  ==@PathVariable== Long id
├─ 查询串          ?name=Tom      ┤  @RequestParam String name
├─ 表单/JSON       body           ┤  @RequestBody UserDTO dto
├─ 请求头          token          ┤  @RequestHeader("token") String t
└─ Cookie          sid            ┤  @CookieValue("sid") String sid
