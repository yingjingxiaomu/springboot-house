package com.jk.controller.house;


import com.jk.model.house.*;
import com.jk.model.staff.EmpBean;
import com.jk.service.house.HouseService;

import com.jk.utils.OssClienUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.util.*;

@Controller
@RequestMapping(value = "house")
public class HouseController {

    @Autowired
    private HouseService houseService;



    /**
     * 新增房屋 修改
     * @param srb
     */
    @RequestMapping(value = "addhouse")
    @ResponseBody
    public void addhouse(HttpServletRequest request ,SellHouseResourceBean srb){

        HttpSession session = request.getSession();
        EmpBean empBean = (EmpBean)session.getAttribute(session.getId());

        //32位uuid
        //修改
        if(srb.getId()!=""){
            houseService.updatehouse(srb);
        }else{

         Object uuids = request.getSession().getAttribute("uuids");
         String s = uuids.toString();
         srb.setTeId(empBean.getId());
         srb.setId(s);
         srb.setReleaseTime(new Date());
         srb.setRoomNum(String.valueOf(System.currentTimeMillis()));
        //添加
        houseService.addhouse(srb);

        }
    }



    //查询三级联动的省
    @RequestMapping(value = "selectProvince")
    @ResponseBody
    public List<XxArea> selectProvince(){
        List<XxArea> list=houseService.selectProvince();
        return list;
    }

    //根据父级id查询出对应的子集
    @RequestMapping(value = "selectArea")
    @ResponseBody
    public List<XxArea> selectArea(Integer pid){

        return houseService.selectArea(pid);
    }


    /**
     * 跳转查看详情页面
     * @return
     */
        @RequestMapping(value = "lookhouse")
    public String lookhouse(HttpServletRequest request,String id,Model model){

        SellHouseResourceBean newhouse = houseService.getHouseDetailsId(id);
            //县
            List<XxArea> areacity=  houseService.selectAreatwo(newhouse.getCounty());
            XxArea cityBean = new XxArea();
        for (XxArea areacitys : areacity) {
            cityBean.setId(areacitys.getId());
            cityBean.setName(areacitys.getName());
        }

            //市
            XxArea countryBean = new XxArea();
            List<XxArea> areacountry= houseService.selectAreatwo(newhouse.getCity());
            for (XxArea xxArea : areacountry) {

                countryBean.setId(xxArea.getId());
                countryBean.setName(xxArea.getName());
            }

        model.addAttribute("newhouse",newhouse);
        HttpSession session = request.getSession();

        session.setAttribute("city",cityBean);//县

        session.setAttribute("country",countryBean);//市


        return "house/addhouse";


    }


    /**
     * 跳转到添加房屋页面
     * @param request
     * @return
     */
    @RequestMapping(value = "toAddhouseHtml")
    public String toAddhouseHtml(HttpServletRequest request){
        //32位uuid
        String uuids = UUID.randomUUID().toString().replace("-", "");
        HttpSession session = request.getSession();
        session.setAttribute("uuids",uuids);
        System.out.println(uuids);
        return "house/addhouse";
    }

    /**
     * oss 上传图片 支持多图
     * @param request
     * @param file
     * @param typeId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "headImgUpload")
    @ResponseBody
    public Map<String,Object> headImgUpload(HttpServletRequest request, MultipartFile[] file, Integer typeId)throws Exception {
        Object uuids = request.getSession().getAttribute("uuids");
        String s = uuids.toString();
        Map<String,Object> map=new HashMap<>();
        List list=new ArrayList();
        ArrayList<SellHousePicBean> sell=new ArrayList<SellHousePicBean>();
        for (int i = 0; i <file.length; i++) {
            String url = updateHead(file[i]);
            String uuidss = UUID.randomUUID().toString().replace("-", "");
            SellHousePicBean sellHousePicBean=new SellHousePicBean();
            sellHousePicBean.setUrl(url);
            sellHousePicBean.setId(uuidss);
            sellHousePicBean.setTsId(s);
            sellHousePicBean.setType(typeId);
            sell.add(sellHousePicBean);
            list.add(url);

        }
        boolean isok = houseService.addHousePic(sell);
        System.out.println(isok);
        map.put("urllist",list);

        return map;
    }

    /**
     * 上传 oss 图片
     * @param file
     * @return
     * @throws Exception
     */
    public String updateHead(MultipartFile file) throws Exception {
        if (file == null || file.getSize() <= 0) {
            throw new Exception("头像不能为空");
        }
        String  nameHz= file.getOriginalFilename(); //上传的文件名 + 后缀    如  asd.png
        String type = "";
        if(nameHz.contains(".png") || nameHz.contains(".jpg")){
            type="/img";
        }
        if(nameHz.contains(".mp4") || nameHz.contains(".ogv")){
            type="/video";
        }else {
            type="file";
        }
        OssClienUtils ossClient = new OssClienUtils();
        String keyName = ossClient.uploadImg2Oss(file,type);
        String imgUrl = ossClient.getImgUrl(keyName);
        return imgUrl;
    }

    /**
     * 查看房子装修程度
     * @return
     */
    @RequestMapping(value = "selectDecorate")
    @ResponseBody
    public List<DecorateBean> selectDecorate(){
        List<DecorateBean> list=houseService.selectDecorate();
        return list;

    }

    /**
     * 查看房子的类型
     * @return
     */
    @RequestMapping(value = "selectHouseType")
    @ResponseBody
    public List<HouseTypeBean> selectHouseType(){

        List<HouseTypeBean> list=houseService.selectHouseType();
        return list;
    }

    /**
     * 跳转到查询页面
     */

  /*  @RequestMapping(value = "tiaocx")
    public String toQeryHouse(){

        return "house/lookh";
    }*/

    @RequestMapping(value = "tiaocx")
    public String toQeryHouse(){

        return "house/houselist";
    }

    /**
     * 房源查询 分页
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value ="queryHouseList")
    @ResponseBody
    public String queryHouseList(Integer page, Integer limit){

        String houselist= houseService.queryHouseList(page,limit);

        return  houselist;
    }


    /**
     * 跳转查看详情页面
     * @return
     */
    @RequestMapping(value = "lookhxq")
    public String toHouseDetailsLL(String id,Model model){

        SellHouseResourceBean newhouse = houseService.getHouseDetailsId(id);
        model.addAttribute("newhouse",newhouse);
        return "house/qqqq";
    }


    /**
     * 通过id删除房源
     * @param id
     * @return
     */
    @RequestMapping(value = "delHouseById")
    @ResponseBody
    public void delHouseById(String id){
        houseService.delHouseById(id);

    }


    /**
     * 批量删除房源
     * @param ids
     * @return
     */
    @RequestMapping(value = "delAllHouseByIds")
    @ResponseBody
    public Map<String,Object> delAllHouseByIds(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            houseService.delAllHouseByIds(ids);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }

}
