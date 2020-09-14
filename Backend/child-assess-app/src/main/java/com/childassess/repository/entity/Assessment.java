package com.childassess.repository.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "assessment")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Assessment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "assessment_id")
	@JsonProperty("assessment_id")
	private int assessmentId;

	@OneToOne
	private ChildProfile child;

	@OneToOne
	private Dimension dimension;

	@ManyToMany(mappedBy = "assessment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AnswerOption> answers;

	public int getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}

	public ChildProfile getChild() {
		return child;
	}

	public void setChild(ChildProfile child) {
		this.child = child;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public List<AnswerOption> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerOption> answers) {
		this.answers = answers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + assessmentId;
		result = prime * result + ((child == null) ? 0 : child.hashCode());
		result = prime * result + ((dimension == null) ? 0 : dimension.hashCode());
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
		Assessment other = (Assessment) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (assessmentId != other.assessmentId)
			return false;
		if (child == null) {
			if (other.child != null)
				return false;
		} else if (!child.equals(other.child))
			return false;
		if (dimension == null) {
			if (other.dimension != null)
				return false;
		} else if (!dimension.equals(other.dimension))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assessment [assessmentId=" + assessmentId + ", child=" + child + ", dimension=" + dimension
				+ ", answers=" + answers + "]";
	}

}