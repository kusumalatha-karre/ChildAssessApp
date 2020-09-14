import React from 'react';
import PropTypes from 'prop-types';
import { CSSTransitionGroup } from 'react-transition-group';
import Question from '../components/Question';
import QuestionCount from '../components/QuestionCount';
import AnswerOption from '../components/AnswerOption';

function Quiz(props) {
  function renderAnswerOptions(key) {
	console.log(key);
    return (
      <AnswerOption
        id={key.id}
        content={key.content}
        questionId={props.questionId}
        answer={props.answer}
        onAnswerSelected={props.handleAnswerSelected}
      />
    );
  }

  return (
     <div style={{width:"100%"}} key={props.questionId}>
        <QuestionCount counter={props.questionId} total={props.questionTotal} />
        <Question content={props.question} />
		<div style={{width:"500px"}}>
        <ul className="answerOption">
          {props.options.map(renderAnswerOptions)}
        </ul>
		</div>
      </div>    
  );
}

Quiz.propTypes = {
  questionId: PropTypes.number.isRequired,
  question: PropTypes.string.isRequired,
  answer: PropTypes.string.isRequired,
  options: PropTypes.array.isRequired,
  questionTotal: PropTypes.number,
  onAnswerSelected: PropTypes.func.isRequired
};

export default Quiz;
