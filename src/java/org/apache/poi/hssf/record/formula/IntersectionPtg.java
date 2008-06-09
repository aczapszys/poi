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

package org.apache.poi.hssf.record.formula;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Daniel Noll (daniel at nuix dot com dot au)
 */
public final class IntersectionPtg extends OperationPtg {
    public final static byte sid  = 0x0f;

    public static final OperationPtg instance = new IntersectionPtg();

    private IntersectionPtg() {
    	// enforce singleton
    }

    public final boolean isBaseToken() {
        return true;
    }

    public int getSize()
    {
        return 1;
    }

    public void writeBytes( byte[] array, int offset )
    {
        array[ offset + 0 ] = sid;
    }

    /** Implementation of method from Ptg */
    public String toFormulaString(Workbook book)
    {
        return " ";
    }


    /** implementation of method from OperationsPtg*/
    public String toFormulaString(String[] operands)
    {
         StringBuffer buffer = new StringBuffer();

         buffer.append(operands[ 0 ]);
         buffer.append(" ");
         buffer.append(operands[ 1 ]);
         return buffer.toString();
     }

    public int getNumberOfOperands()
    {
        return 2;
    }

}
