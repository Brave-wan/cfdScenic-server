package com.htkj.cfdScenic.app.service;

import com.htkj.cfdScenic.app.dao.PictureLibraryDao;
import com.htkj.cfdScenic.app.model.PictureLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangfenglong
 * @date 2016/10/17 001716:13.
 */
@Service
@Transactional
public class PictureLibraryService {

    @Autowired
    private PictureLibraryDao pictureLibraryDao;

    public void savePictureLibraryAll(PictureLibrary pl) {
        pictureLibraryDao.savePictureLibraryAll(pl);
    }

    public List selectByLinkId(Long id) {
        return pictureLibraryDao.getPicById(id);
    }

    public void deleteByLinkId(Long id) {
        pictureLibraryDao.deleteByLinkId(id);
    }
}
