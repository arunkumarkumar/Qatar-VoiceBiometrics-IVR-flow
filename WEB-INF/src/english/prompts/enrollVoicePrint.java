/**
 * Last generated by Orchestration Designer at: 2021-JAN-29  12:39:44 PM
 */
package english.prompts;

public class enrollVoicePrint extends com.avaya.sce.runtime.Prompt {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Constructor for enrollVoicePrint.
	 */
	public enrollVoicePrint() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		//}}END:CLASS:CONSTRUCTOR
	}


	/**
	 * This method is generated automatically and should not be manually edited
	 * To manually edit the prompt, override:
	 * void updatePrompt()
	 * Last generated by Orchestration Designer at: 2024-MAR-29  10:23:36 AM
	 */
	public void buildPrompt(){
		com.avaya.sce.runtime.Format format = null;
		com.avaya.sce.runtime.RenderHint hint = null;
		com.avaya.sce.runtime.MediaPage mediaPage = null;
		setBarginType(com.avaya.sce.runtimecommon.SCERT.BARGIN_SPEECH);
		setName("enrollVoicePrint");
		setOrder(com.avaya.sce.runtime.Prompt.STANDARD);

		// Prompt level 1
		setTimeout(1,8000);
		setBargin(1,true);

		if ( new com.avaya.sce.runtime.Condition( "condition", "EnrollWS:playPhraseFile", com.avaya.sce.runtime.Expression.STRING_EQUAL_IGNORE, "2", false, null ).evaluate(getSession()) == true ) {
			add(1, new com.avaya.sce.runtime.PromptElement(com.avaya.sce.runtime.PromptElement.PHRASESET,"phraseEng:RECORD_PRINT1",false));
		}

		else if ( new com.avaya.sce.runtime.Condition( "condition", "EnrollWS:playPhraseFile", com.avaya.sce.runtime.Expression.STRING_EQUAL_IGNORE, "3", false, null ).evaluate(getSession()) == true ) {
			add(1, new com.avaya.sce.runtime.PromptElement(com.avaya.sce.runtime.PromptElement.PHRASESET,"phraseEng:RECORD_PRINT2",false));
		}

		else {
			add(1, new com.avaya.sce.runtime.PromptElement(com.avaya.sce.runtime.PromptElement.PHRASESET,"phraseEng:VB_VOICEPLAY",false));
		}

	}
}
