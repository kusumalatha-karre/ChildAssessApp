package com.childassess.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "answer_option")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerOption {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "option_id")
	@JsonProperty("option_id")
	private int optionId;

	@Column(name = "option_txt")
	@JsonProperty("option_txt")
	private String optionText;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id", nullable = false)
	private List<Assessment> assessment;

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Assessment> getAssessment() {
		return assessment;
	}

	public void setAssessment(List<Assessment> assessment) {
		this.assessment = assessment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assessment == null) ? 0 : assessment.hashCode());
		result = prime * result + optionId;
		result = prime * result + ((optionText == null) ? 0 : optionText.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnswerOption other = (AnswerOption) obj;
		if (assessment == null) {
			if (other.assessment != null)
				return false;
		} else if (!assessment.equals(other.assessment))
			return false;
		if (optionId != other.optionId)
			return false;
		if (optionText == null) {
			if (other.optionText != null)
				return false;
		} else if (!optionText.equals(other.optionText))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AnswerOption [optionId=" + optionId + ", optionText=" + optionText + ", question=" + question
				+ ", assessment=" + assessment + "]";
	}

}