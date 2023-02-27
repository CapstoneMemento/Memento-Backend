package start17.Memento.domain.search;

public class NoteForm {
    private String title;
    private String content;

    public NoteForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
