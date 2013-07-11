package org.zkoss.zktest.test2.F65

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test

@Tags(tags = "F65-ZK-1277.zul")
class F65_ZK_1277Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript = """<?xml version="1.0" encoding="UTF-8"?>

<!--
F65-ZK-1277.zul

	Purpose:
		
	Description:
		
	History:
		Mon, Jul 30, 2012  5:18:40 PM, Created by jumperchen

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

-->
<zk>
	1. Please check each row should be the same height(group, row, and groupfoot)
	<separator/>
	2. Please click the "autopaging" button, and check the same as step 1 again.
	<button label="Autopaging" onClick='grid.autopaging = !grid.autopaging'/>
	<grid id="grid" fixedLayout="true">
		<columns sizable="true">
			<column label="Brand" />
			<column label="Processor Type" width="150px"/>
			<column label="Memory (RAM)" width="120px"/>
			<column label="Price"  width="100px"/>
			<column label="Hard Drive Capacity" width="150px"/>
		</columns>
		<rows>
			<group label="Dell"/>
			<row>
				<label style="padding-left:15px" value="Dell E4500 2.2GHz"/>
				<label value="Intel Core 2 Duo"/>
				<label value="2GB RAM"/>
				<label value="$261.00" style="color:green"/>
				<label value="500GB"/>
			</row>
			<row>
				<label style="padding-left:15px" value="XP-Pro Slim Dell-Inspiron-530-s"/>
				<label value="Intel Core 2 Duo"/>
				<label value="2GB RAM"/>
				<label value="$498.93" style="color:green"/>
				<label value="500GB"/>				
			</row>
			<row>
				<label style="padding-left:15px" value="Dell P4 3.2 GHz"/>
				<label value="Intel Pentium 4"/>
				<label value="4GB RAM"/>
				<label value="$377.99" style="color:green"/>
				<label value="500GB"/>				
			</row>
			<group label="Compaq"/>
			<row>
				<label style="padding-left:15px" value="Compaq SR5113WM"/>
				<label value="Intel Core Duo"/>
				<label value="1GB RAM"/>
				<label value="$279.00" style="color:green"/>
				<label value="160GB"/>				
			</row>
			<row>
				<label style="padding-left:15px" value="Compaq HP XW4200"/>
				<label value="Intel Pentium 4"/>
				<label value="4GB RAM"/>
				<label value="$980" style="color:green"/>
				<label value="500GB"/>				
			</row>
			<groupfoot spans="5">
				<label value="This a summary about Compaq Desktop PCs"/>
			</groupfoot>
		</rows>
	</grid>
</zk>
			
"""
    runZTL(zscript,
      () => {
        val verifyHeight = (result: Int) =>
          List(".z-row", ".z-groupfoot") foreach { cls =>
            verifyTrue("each row should be the same height", jq(cls).outerHeight() == result)
          }
        verifyHeight(jq(".z-group").outerHeight())
        click(jq(".z-button:contains(Autopaging)"))
        waitResponse()
        verifyHeight(jq(".z-group").outerHeight())
      })

  }
}