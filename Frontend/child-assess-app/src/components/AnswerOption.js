import React from 'react';
import PropTypes from 'prop-types';

function AnswerOption(props) {
  console.log('Answer options :: '+props);
  return (
    <li className="answerOption">
      <input
        type="radio"
        className="radioCustomButton"
        name="radioGroup"
        checked={props.id === props.answer}
        id={props.id}
        value={props.content}
        disabled={props.answer}
        onChange={props.onAnswerSelected}
      />
      <label className="radioCustomLabel" htmlFor={props.id}>
        {props.content}
      </label>
    </li>
  );
}

AnswerOption.propTypes = {
  id: PropTypes.number.isRequired,
  questionId: PropTypes.number.isRequired,
  content: PropTypes.string.isRequired,
  answer: PropTypes.string.isRequired,
  onAnswerSelected: PropTypes.func.isRequired
};

export default AnswerOption;
