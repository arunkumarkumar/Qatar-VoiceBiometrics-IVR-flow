package flow.subflow.Enrollment;

import java.util.Collection;
import java.util.HashMap;

import com.avaya.sce.runtime.Prompt;
import com.avaya.sce.runtimecommon.ITraceInfo;
import com.avaya.sce.runtimecommon.SCESession;
import com.util.GlobalConstant;

import flow.IProjectVariables;

/**
 * Class that represents a form.  A form can contain prompts, fields  and links
 * as well as handlers for various events that may occur while the form is executing
 * Last generated by Orchestration Designer at: 2023-DEC-26  09:52:12 AM
 */
public class enrollsuccessannouncement extends com.avaya.sce.runtime.Form {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Default constructor
	 * Last generated by Orchestration Designer at: 2023-DEC-26  09:52:12 AM
	 */
	public enrollsuccessannouncement() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		setNeedsDefaultDisconnectHandler(false);
		//}}END:CLASS:CONSTRUCTOR
	}

	/**
	 * This method is generated automatically and should not be manually edited.
	 * To manually edit the links for the node, override:
	 *     void updateLinks(Collection links, SCESession mySession)
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 * @return a collection of links
	 */
	public java.util.Collection getLinks(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Link link;
		java.util.List list;
		java.util.Collection grammarInfo = null;
		java.util.Collection<com.avaya.sce.runtime.CaptureExpression> captureExpr = null;
		// This item does not have any defined links
		list = new java.util.ArrayList();
		String ___tempGrammarName = null;

		return(list);
	}
	/**
	 * This method is generated automatically and should not be manually edited.
	 * To manually edit the properties for the node, override:
	 *     void updateProperties(Collection properties, SCESession mySession)
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 * @return a collection of properties
	 */
	public java.util.Collection getProperties(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Property property;
		java.util.List list;
		// This item does not have any defined properties
		list = new java.util.ArrayList();

		return(list);
	}
	/**
	 * This method is generated automatically and should not be manually edited.
	 * To manually edit the event handlers for the node, override:
	 *    void updateEvents(Collection events, SCESession mySession)
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 * @return a collection of Events
	 */
	public java.util.Collection getEvents(com.avaya.sce.runtimecommon.SCESession mySession) {
		java.util.List list;
		com.avaya.sce.runtime.Event event;
		list = new java.util.ArrayList();
		java.util.List eventPromptNames = null;
		String ___tempPromptName = null;
		return(list);
	}
	/**
	 * Builds the list of fields for the form.  This list is built
	 * by the code generator based on items in the call flow editor.
	 * This method should not be manually edited as changes may be
	 * overwritten by the code generator.  <br>
	 * To update the fields for the form, override
	 *     public void updateFields(Collection fields, com.avaya.sce.runtimecommon.SCESession mySession)
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 */
	public java.util.Collection getFields(com.avaya.sce.runtimecommon.SCESession mySession) {
		java.util.List list = null;
		java.util.List promptNames = null;
		java.util.List eventPromptNames = null;
		java.util.List grammarInfo = null;
		java.util.List events = null;
		java.util.List properties = null;
		com.avaya.sce.runtime.Field field = null;
		com.avaya.sce.runtime.Event event = null;
		com.avaya.sce.runtime.Property property = null;
		String ___tempPromptName = null;
		String ___tempGrammarName = null;

		int nbest = 0;

		// Form has no fields associated
		list = new java.util.ArrayList();

		return(list);
	}
	/**
	 * Builds the list of prompts that are used by this flow item<br>
	 * This method is generated automatically and changes to it may
	 * be overwritten next time code is generated.  To modify the list
	 * of prompts for the flow item, override:
	 *     updatePrompts(Collection prompts, SCESession mySession)
	 * @return list of prompts for the menu
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 */
	public java.util.Collection getPrompts(com.avaya.sce.runtimecommon.SCESession mySession) {
		java.util.List list = null;
		String ___tempPromptName = null;
		// Item has no prompts associated
		list = new java.util.ArrayList();

		return(list);
	}
	/**
	 * Returns the submit item that identifies the next form to transition to.
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 */
	public com.avaya.sce.runtime.Submit getSubmit(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Submit submit = null;
		submit = new com.avaya.sce.runtime.Submit("Enrollment-UPDATEIVRDB");
		return submit;
	}
	/**
	 * Returns the record item for the form.
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 */
	public com.avaya.sce.runtime.Record getRecord(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Record record = null;
		java.util.Collection events = null;
		java.util.Collection promptNames = null;
		java.util.Collection eventPromptNames = null;
		java.util.Collection grammarInfo = null;
		com.avaya.sce.runtime.Event event = null;
		return record;
	}
	/**
	 * Returns the transfer item for the form.
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 */
	public com.avaya.sce.runtime.Transfer getTransfer(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Transfer transfer = null;
		return transfer;
	}
	/**
	 * Returns a Throw object that will throw an event at runtime if set.
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 */
	public com.avaya.sce.runtime.Throw getThrow(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Throw event = null;
		return event;
	}
	/**
	 * Builds the list of tracking items that are generated by this flow item<br>
	 * This method is generated automatically and changes to it may
	 * be overwritten next time code is generated.
	 * @return list of tracking items for the item
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 */
	public java.util.Collection getTrackingActions(com.avaya.sce.runtimecommon.SCESession mySession) {
		java.util.List list = null;

		// Item has no tracking items.
		list = new java.util.ArrayList();
		return(list);
	}
	/**
	 * Returns a collection of platform objects, or extensions to VXML.
	 * These objects result in the generation of the VXML object tag that
	 * the VXML platform attempts to use to load the runtime library that implements
	 * the extension.
	 * 
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 */
	public java.util.Collection getPlatformObjects(com.avaya.sce.runtimecommon.SCESession mySession) {
		java.util.Collection objectList = null;
		java.util.Collection promptNames = null;
		com.avaya.sce.runtime.PlatformObject platformObject = null;

		// Form has no extension objects defined
		objectList = new java.util.ArrayList();
		return(objectList);
	}
	/**
	 * Builds the initial element for the form.  This method is built
	 * by the code generator based on items in the call flow editor.
	 * This method should not be manually edited as changes may be
	 * overwritten by the code generator.  <br>Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 */
	public com.avaya.sce.runtime.Initial getInitial(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Initial initial = null;
		java.util.List promptNames = null;
		java.util.List eventPromptNames = null;
		java.util.List events = null;
		com.avaya.sce.runtime.Event event = null;
		return initial;
	}
	/**
	 * Builds the grammar elements for the form.  This method is built
	 * by the code generator based on items in the call flow editor.
	 * This method should not be manually edited as changes may be
	 * overwritten by the code generator.  <br>Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 */
	public java.util.Collection getGrammars(com.avaya.sce.runtimecommon.SCESession mySession) {
		java.util.List grammarInfo = null;
		grammarInfo = new java.util.ArrayList();

		return grammarInfo;
	}
	
	@Override
	public void updatePrompts(Collection prompts, SCESession mySession) {

		
		try 
		{
			super.updatePrompts(prompts, mySession);
			Prompt Speak = new Prompt()
			{
				@Override
				public void buildPrompt()
				{
					
				}
			};
			Speak.setSession(mySession);
			com.avaya.sce.runtime.Format format = null;
			format = new com.avaya.sce.runtime.Format();
			format.add(com.avaya.sce.runtime.Format.FORMAT,com.avaya.sce.runtime.Format.FMT_FILEURL);
			String promptFullyQualifiedPath ;
			int phraseVarCounter = 0;
			String promptName="";
			String exitpoint =mySession.getVariableField(IProjectVariables.RETRY,IProjectVariables.RETRY_FIELD_POINT).getStringValue();
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "********* exitpoint ********* : "+exitpoint);
			
				promptName = "ENROLLSUCCESS";
			
			HashMap<String,String>  App_Prop = new HashMap<String, String>();
			App_Prop = (HashMap<String, String>) mySession.getVariableField("HashMap", "PropertyValue").getObjectValue();
			String sudioPath = App_Prop.get("AudioLocation").toString();
			String language = mySession.getVariableField("ApplicationVariable", "Language").getStringValue();
			promptFullyQualifiedPath= sudioPath +language+GlobalConstant.SEPARATOR_FORWARD_SLASH+promptName.trim()+GlobalConstant.EXTENSION_WAVE;
			phraseVarCounter = (phraseVarCounter+1);
			String phraseVarName = GlobalConstant.VAR_COMPLEX_PHRASEVAR+GlobalConstant.SEPARATOR_COLON+GlobalConstant.VAR_COMPLEX_PHRASEVAR_FIELD+(phraseVarCounter);
	 
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "********* wav ********* : "+phraseVarName);
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "********* AudioLocation ********* : "+App_Prop.get("AudioLocation").toString());
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "********* Language ********* : ");//+App_Prop.get("Language").toString());
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_DEBUG, "********* promptFullyQualifiedPath ********* : "+promptFullyQualifiedPath);
			
			mySession.getVariableField(IProjectVariables.PHRASEVAR,GlobalConstant.VAR_COMPLEX_PHRASEVAR_FIELD+(phraseVarCounter)).setValue(promptFullyQualifiedPath);
			Speak.add(1, new com.avaya.sce.runtime.PromptElement(com.avaya.sce.runtime.PromptElement.VARIABLE_TEXT,phraseVarName, format));
			prompts.add(Speak);
		} 
		catch (java.lang.Exception ex) 
		{
			mySession.getTraceOutput().writeln(ITraceInfo.TRACE_LEVEL_FATAL, "Exception "+ex);
		}
	
	}
}
