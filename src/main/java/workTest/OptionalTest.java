package workTest;

/**
 * @author neptune
 * @create 2020 06 04 9:09 下午
 */
public class OptionalTest {
    /**
     * 在service层中查询一个对象，返回之后判断是否为空并做处理
     *
     * 场景 1：
     *      //查询一个对象
     *      Member member = memberService.selectByIdNo(request.getCertificateNo());
     *
     *      //使用ofNullable加orElseThrow做判断和操作
     *      Optional.ofNullable(member).orElseThrow(() -> new ServiceException("没有查询的相关数据"));
     *
     *
     * 场景2：
     *      我们可以在dao接口层中定义返回值时就加上Optional 例如：我使用的是jpa，其他也同理
     *
     *      public interface LocationRepository extends JpaRepository<Location, String> {
     *           Optional<Location> findLocationById(String id);
     *      }
     *
     *      然后在是Service中
     *      public TerminalVO findById(String id) {
     *              //这个方法在dao层也是用了Optional包装了
     *              Optional<Terminal> terminalOptional = terminalRepository.findById(id);
     *              //直接使用isPresent()判断是否为空
     *              if (terminalOptional.isPresent()) {
     *              //使用get()方法获取对象值
     *                  Terminal terminal = terminalOptional.get();
     *                  //在实战中，我们已经免去了用set去赋值的繁琐，直接用BeanCopy去赋值
     *                  TerminalVO terminalVO = BeanCopyUtils.copyBean(terminal, TerminalVO.class);
     *                  //调用dao层方法返回包装后的对象
     *                  Optional<Location> location = locationRepository.findLocationById(terminal.getLocationId());
     *                  if (location.isPresent()) {
     *                      terminalVO.setFullName(location.get().getFullName());
     *                  }
     *                  return terminalVO;
     *              }
     *              //不要忘记抛出异常
     *              throw new ServiceException("该终端不存在");
     *          }
     */
}
