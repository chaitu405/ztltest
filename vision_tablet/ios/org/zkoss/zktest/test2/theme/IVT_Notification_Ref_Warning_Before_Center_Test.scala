package org.zkoss.zktest.test2.theme

import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags
import org.zkoss.ztl.Tags

@Tags(tags = "IOS,VisionTest")
class IVT_Notification_Ref_Warning_Before_Center_Test extends ZTL4ScalaTestCase {
	def testClick() = {
		val zscript = """
<zk>
	<div style="padding: 150px 250px">
		<window id="win" border="normal" title="Window" height="300px" width="300px">
			Window Content
		</window>
	</div>
	
	<zscript>
	<![CDATA[
	import org.zkoss.zk.ui.util.Clients;
	import org.zkoss.zk.ui.Component;
	
	String msg = "Use the force, Harry.";
	String type = Clients.NOTIFICATION_TYPE_WARNING;
	Component ref = win;
	String pval = "before_center";
	int dur = -1;
	boolean closable = false;
	
	Clients.showNotification(msg, type, ref, pval, dur, closable);
	]]>
	</zscript>
</zk>
		""";

		runZTL(zscript,
			() => {
				verifyImage();
			});
	}
}