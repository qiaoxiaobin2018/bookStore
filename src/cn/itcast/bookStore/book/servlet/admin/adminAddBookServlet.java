package cn.itcast.bookStore.book.servlet.admin;

import cn.itcast.bookStore.book.domain.Book;
import cn.itcast.bookStore.book.service.BookService;
import cn.itcast.bookStore.category.Service.CategoryService;
import cn.itcast.bookStore.category.domain.Category;
import cn.itcast.commons.CommonUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "adminAddBookServlet",urlPatterns = "/admin/adminAddBookServlet")
public class adminAddBookServlet extends HttpServlet {
    private BookService bookService = new BookService();
    private CategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//请求编码（POST）
        response.setContentType("text/html;charset=utf-8");//响应编码

        /*
        * 1. 把表单数据封装到 Book 对象中
        *       文件上传三步
        * */
        //创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory(20*1024,new File("D:\\upload_tmp"));
        //得到解析器
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //设置单个文件大小限制 15KB
        sfu.setFileSizeMax(20*1024);

        Map<String,String> map = new HashMap<String,String>();
        Map<String,String> mapErr = new HashMap<String,String>();

        try {
            //使用解析器解析  request , 得到 List<FileItem>
            List<FileItem> fileItemList = sfu.parseRequest(request);
            /*
            * 把 fileItemList 中的数据封装到 Book 对象中
            *   > 先把所有的普通表单字段数据封装到 Map 中
            *   > 再把 map 中的数据封装到 Book 对象中
            * */
            //遍历 fileItemList
            for(FileItem fileItem:fileItemList){
                if(fileItem.isFormField()){
                    //是普通表单项
                    String fname = fileItem.getFieldName();
                    String fvalue = fileItem.getString("UTF-8");
                    if(fvalue.isEmpty()){
                        mapErr.put(fname,"此项不能为空！");
                    }
                    else{
                        map.put(fname,fvalue);
                    }
                }
            }
            //校验
            if(mapErr.size() > 0){
                /*
                * 保存 map,mapErr 到 request 中，
                * 转发到 add.jsp 中显示
                * */
                request.setAttribute("map",map);
                request.setAttribute("mapErr",mapErr);
                request.setAttribute("categoryList",categoryService.findAll());
                request.getRequestDispatcher("/admin/book/add.jsp").forward(request,response);
                return;
            }


            //将 map 转化为 Book 对象
            Category category = CommonUtils.toBean(map,Category.class);
            Book book = CommonUtils.toBean(map,Book.class);
            book.setCategory(category);
            //为  Book 指定 bid
            book.setBid(CommonUtils.uuid());

            /*
            * 保存上传的文件
            * */
            //得到文件保存目录
            String savePath = this.getServletContext().getRealPath("/book_image");

            System.out.println("savePath:" + savePath);

            //得到保存文件名称
            /*
            * 注意：上传时文件中带有中文名称的话，加载图片是会出问题，所以图片名称中不要有中文
            * */
            String originFileName = fileItemList.get(3).getName();
            /*
            *  校验文件
            * */
            if(originFileName.isEmpty()){
                /*
                 * 必须要有文件 并且 文件名不为空
                 * 保存 map,mapErr到 request 中，
                 * 转发到 add.jsp 中显示
                 * */

                System.out.println("****文件名为空！****");

                mapErr.put("image","必须选择文件！");
                request.setAttribute("map",map);
                request.setAttribute("mapErr",mapErr);
                request.setAttribute("categoryList",categoryService.findAll());
                request.getRequestDispatcher("/admin/book/add.jsp").forward(request,response);
                return;
            }
            else if(!originFileName.toLowerCase().endsWith("jpg")){
                mapErr.put("image","图片格式必须为 jpg！");
                request.setAttribute("map",map);
                request.setAttribute("mapErr",mapErr);
                request.setAttribute("categoryList",categoryService.findAll());
                request.getRequestDispatcher("/admin/book/add.jsp").forward(request,response);
                return;
            }
            String fileName = CommonUtils.uuid() + "_" + originFileName;

            System.out.println("fileName:" + fileName);

            //使用目录和文件名称创建目标文件
            File destFile = new File(savePath, fileName);
            //写入文件
            fileItemList.get(3).write(destFile);

            /*
            * 校验图片的尺寸 不超过 150*150
            * */
            ImageIcon imageIcon = new ImageIcon(destFile.getAbsolutePath());
            Image image = imageIcon.getImage();
            if(image.getHeight(null) > 200 || image.getWidth(null) > 150){
                destFile.delete();//删除已经保存的图片
                mapErr.put("image","图片大小不超过 150x150！");
                request.setAttribute("map",map);
                request.setAttribute("mapErr",mapErr);
                request.setAttribute("categoryList",categoryService.findAll());
                request.getRequestDispatcher("/admin/book/add.jsp").forward(request,response);
                return;
            }

            /*
            * 保存文件的项目路径到 Book 对象中
            * */
            book.setImage("book_image/" + fileName);

            //调用 BookService#add() 方法保存
            bookService.add(book);
            //返回所有图书列表
            request.getRequestDispatcher("/admin/adminBookServlet?method=findAll").forward(request,response);
        } catch (Exception e) {
            if(e instanceof FileUploadBase.FileSizeLimitExceededException){
                //因为文件大小而产生了异常
                /*
                 * 保存 map,mapErr到 request 中，
                 * 转发到 add.jsp 中显示
                 * */
                request.setAttribute("msg","图片大小不能超过 15 KB！");
                request.setAttribute("categoryList",categoryService.findAll());
                request.getRequestDispatcher("/admin/book/add.jsp").forward(request,response);
                return;
            }else{
                request.setAttribute("msg","添加图书失败！");
            }
            request.getRequestDispatcher("/admin/adminBookServlet?method=findAll").forward(request,response);
        }
    }
}
