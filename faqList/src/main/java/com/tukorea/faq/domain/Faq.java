package com.tukorea.faq.domain;

public class Faq {

    private int faqSeq;
    private String category;
    private String question;
    private String answer;
    private String redDt;



    public int getFaqSeq() {
        return faqSeq;
    }

    public void setFaqSeq(int faqSeq) {
        this.faqSeq = faqSeq;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRedDt() {
        return redDt;
    }

    public void setRedDt(String redDt) {
        this.redDt = redDt;
    }
}
