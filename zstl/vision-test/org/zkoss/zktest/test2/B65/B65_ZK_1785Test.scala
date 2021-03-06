package org.zkoss.zktest.test2.B65

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test

@Tags(tags = "B65-ZK-1785.zul")
class B65_ZK_1785Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript = """<zk>
  <div hflex="1" vflex="1">
    <vlayout>
      "Select a tab below: check the correct tabpanel content is opened each time"
    </vlayout>
    <tabbox id="tabbox" mold="accordion">
      <tabs>
      	<tab id="tab0">0</tab>
      	<tab id="tabA">A</tab>
      	<tab id="tabB">B</tab>
      	<tab>
      		<attribute name="onClick">
      			/*1*/tabbox.getTabs().removeChild(tabB);
      			/*2*/tabbox.getTabpanels().removeChild(panelB);
      		</attribute>
      		C (click will remove B and open C)
      	</tab>
      	<tab>
      		<attribute name="onClick">
      			//same code as above only the following 2 lines are switched
      			
      			/*2*/tabbox.getTabpanels().removeChild(panelA);
      			/*1*/tabbox.getTabs().removeChild(tabA);
      		</attribute>
      		D (click will remove A and should open D) before fix E got opened 
	    </tab>
      	<tab>E
      		<attribute name="onClick">
      			tab0.close();
      		</attribute>
      		E (click will remove 0 and open E)
      	</tab>
      	<tab>F</tab>
      	<tab id="tabG">G</tab>
      	<tab id="tabH">H</tab>
      	<tab id="tabI">I</tab>
      	<tab>J</tab>
      	<tab>
      		<attribute name="onClick">
      			tabbox.getTabpanels().removeChild(panelG);
      			tabbox.getTabs().removeChild(tabI);
      			tabbox.getTabs().removeChild(tabG);
      			tabH.close();
      			tabbox.getTabpanels().removeChild(panelI);
      		</attribute>
      		K (click will remove G,H,I in wild order and should open K) 
      	</tab>
      	<tab>L</tab>
      </tabs>
      <tabpanels>
      	<tabpanel>Content0</tabpanel>
      	<tabpanel id="panelA">ContentA</tabpanel>
      	<tabpanel id="panelB">ContentB</tabpanel>
      	<tabpanel>ContentC</tabpanel>
      	<tabpanel>ContentD</tabpanel>
      	<tabpanel>ContentE</tabpanel>
      	<tabpanel>ContentF</tabpanel>
      	<tabpanel id="panelG">ContentG</tabpanel>
      	<tabpanel>ContentH</tabpanel>
      	<tabpanel id="panelI">ContentI</tabpanel>
      	<tabpanel>ContentJ</tabpanel>
      	<tabpanel>ContentK</tabpanel>
      	<tabpanel>ContentL</tabpanel>
      </tabpanels>
    </tabbox>
  </div>
</zk>
"""
    runZTL(zscript,
      () => {
        'A' to 'L' foreach { char =>
          val tab = if (char != 'E')
            jq(".z-tab-accordion:contains(" + char + ")")
          else
            jq(".z-tab-accordion:contains(" + char + "):eq(1)")
          click(tab)
          waitResponse()
          verifyImage()
        }
      })

  }
}