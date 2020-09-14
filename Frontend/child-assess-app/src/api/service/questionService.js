import foodQuestions from '../mock/foodQuestions';
import speechQuestions from '../mock/speechQuestions';
import specialEducationQuestions from '../mock/specialEducationQuestions';
import cognitiveTherapyQuestions from '../mock/cognitiveTherapyQuestions';
import dozeeQuestions from '../mock/dozeeQuestions.js';

export const getQuestions = function(type) {
	if(type === 'Food')
		return foodQuestions;
	if(type === 'Speech')
		return speechQuestions;
	if(type === 'Special Education')
		return specialEducationQuestions;
	if(type === 'Cognitive therapy')
		return cognitiveTherapyQuestions;
	if(type === 'Dozee')
		return dozeeQuestions;
};
