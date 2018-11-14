package com.ibm.picasso.controller;

import com.ibm.picasso.domain.Album;
import com.ibm.picasso.domain.User;
import com.ibm.picasso.pojo.ResultPojo;
import com.ibm.picasso.service.AlbumService;
import com.ibm.picasso.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("?Album/*")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    private String msg = "";

    @RequestMapping(value = "createAlbum", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResultPojo createAlbum(Album album, HttpSession session) {
        msg = "创建失败";
        album.setStatus(0);
        album.setCreatetime(Util.getNowDate());
        album.setUid((User) session.getAttribute("user"));
        int result = albumService.createAlbum(album);
        if (result > 0) {
            msg = "创建成功";
        }
        return new ResultPojo(album, msg);
    }

    @RequestMapping(value = "updateAlbumName", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResultPojo updateAlbumName(Album album) {
        msg = "修改失败";

        int result = albumService.updateAlbumName(album);

        if (result > 0) {
            msg = "修改成功";
        }


        return new ResultPojo(album, msg);
    }
}
