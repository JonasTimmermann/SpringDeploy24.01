package com.example.gewerbeanmeldung.QuestionCategory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gewerbeanmeldung.Question.Question;

@Service
public class QuestionCategoryService {

	@Autowired
	private QuestionCategoryRepository questionCategoryRepo;

	public void deleteAllCategories(List<QuestionCategory> qc) {
		questionCategoryRepo.deleteAll(qc);
	}

	public List<Question> getQuestionByCategory(String category) {
		QuestionCategory qc = questionCategoryRepo.findByCategory(category);
		return qc.getQuestions();
	}
	public QuestionCategory getByCategoryName(String category) {
		return questionCategoryRepo.findByCategory(category);	
	}
	
	public List<Question> getQuestionByCategoryId(Integer id) {
		QuestionCategory qc = getCategoryById(id);
		return qc.getQuestions();
	}

	public List<QuestionCategory> getAllCategories() {
		List<QuestionCategory> qcs = new ArrayList<>();
		questionCategoryRepo.findAll().forEach(qcs::add);
		
		return qcs;
	}
	public QuestionCategory getCategoryById(Integer id) {
		return questionCategoryRepo.findById(id).orElse(null);
	}
	public String deleteQuestionCategoryById(Integer id){
		questionCategoryRepo.deleteById(id);
		return "deleted category";
	}
	
	public boolean existsByCategoryName(String category) {
		QuestionCategory qc = questionCategoryRepo.findByCategory(category);
		if(qc != null){
			return true;
		}
		return false;
	}
	public List<QuestionCategory> deleteWhenNoMoreExisting(List<QuestionCategory> qcList) {
		List<QuestionCategory> deleteQcs = new ArrayList<QuestionCategory>();
		for(int i = 0; i < qcList.size(); i++) {
		Integer id = qcList.get(i).getId();
		QuestionCategory qc = getCategoryById(id);
		if(qc.getQuestions().size() == 1) {
			questionCategoryRepo.deleteById(id);
			deleteQcs.add(qc);
			}
		}
		return deleteQcs;
	}
}
