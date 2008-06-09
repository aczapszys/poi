/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package org.apache.poi.hslf.model;

import junit.framework.*;

import java.io.FileOutputStream;
import java.io.File;
import java.awt.*;

import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.ddf.EscherBSERecord;

/**
 * Test Picture shape.
 * 
 * @author Yegor Kozlov
 */
public class TestPicture extends TestCase {

    /**
     * Test that the reference count of a blip is incremented every time the picture is inserted.
     * This is important when the same image appears multiple times in a slide show.
     *
     */
    public void testMultiplePictures() throws Exception {
        String cwd = System.getProperty("HSLF.testdata.path");
        SlideShow ppt = new SlideShow();

        Slide s = ppt.createSlide();
        Slide s2 = ppt.createSlide();
        Slide s3 = ppt.createSlide();

        int idx = ppt.addPicture(new File(cwd, "clock.jpg"), Picture.JPEG);
        Picture pict = new Picture(idx);
        Picture pict2 = new Picture(idx);
        Picture pict3 = new Picture(idx);

        pict.setAnchor(new Rectangle(10,10,100,100));
        s.addShape(pict);
        EscherBSERecord bse1 = pict.getEscherBSERecord();
        assertEquals(1, bse1.getRef());

        pict2.setAnchor(new Rectangle(10,10,100,100));
        s2.addShape(pict2);
        EscherBSERecord bse2 = pict.getEscherBSERecord();
        assertSame(bse1, bse2);
        assertEquals(2, bse1.getRef());

        pict3.setAnchor(new Rectangle(10,10,100,100));
        s3.addShape(pict3);
        EscherBSERecord bse3 = pict.getEscherBSERecord();
        assertSame(bse2, bse3);
        assertEquals(3, bse1.getRef());

    }

}
