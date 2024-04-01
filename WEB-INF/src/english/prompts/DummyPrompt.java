/**
 * Last generated by Orchestration Designer at: 2022-MAR-08  04:29:03 PM
 */
package english.prompts;

import java.util.Arrays;

import com.General.AppConstants;
import com.avaya.sce.runtime.tracking.TraceInfo;
import com.avaya.sce.runtimecommon.ITraceInfo;
import com.avaya.sce.runtimecommon.SCESession;

import flow.IProjectVariables;

public class DummyPrompt extends com.avaya.sce.runtime.Prompt {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Constructor for DummyPrompt.
	 */
	public DummyPrompt() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		//}}END:CLASS:CONSTRUCTOR
	}


	/**
	 * This method is generated automatically and should not be manually edited
	 * To manually edit the prompt, override:
	 * void updatePrompt()
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:34 AM
	 */
	public void buildPrompt(){
		com.avaya.sce.runtime.Format format = null;
		com.avaya.sce.runtime.RenderHint hint = null;
		com.avaya.sce.runtime.MediaPage mediaPage = null;
		setBarginType(com.avaya.sce.runtimecommon.SCERT.BARGIN_SPEECH);
		setName("DummyPrompt");
		setOrder(com.avaya.sce.runtime.Prompt.STANDARD);

		// Prompt level 1
		setTimeout(1,8000);
		setBargin(1,false);

	}
	
	@Override
	public void updatePrompt(SCESession mySession) {

		try {

			String PromptLocation = mySession.getVariableField("DynamicPrompt","PromptLocation").getStringValue();

			mySession.getVariableField("Prompt1").setValue(PromptLocation+"/GENERAL/SILENCE.wav");
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "DummyPrompt\t|\tupdatePrompt\t|\tFramedPrompt\t|\t"+mySession.getVariableField("Prompt1").getStringValue(), mySession);

			add(1,new com.avaya.sce.runtime.PhraseVariableElement("Prompt1", com.avaya.sce.runtime.PhraseVariableElement.Type.AUDIO_URL,false));

		} catch(Exception e) {

			mySession.getVariableField(IProjectVariables.ERROR).setValue(true);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "PreAnnouncement\t|\trequestBegin\t|\t"+ AppConstants.EXCEPTION_1+ e.getMessage() +AppConstants.EXCEPTION_2 , mySession);
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "PreAnnouncement\t|\trequestBegin\t|\t"+ Arrays.toString(e.getStackTrace()), mySession);

		}

		super.updatePrompt(mySession);
	}
}