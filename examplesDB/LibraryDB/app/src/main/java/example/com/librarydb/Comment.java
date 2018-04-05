package example.com.librarydb;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiag_000 on 03/04/2017.
 */

@Table(name = "Comments")
public class Comment extends Model {

    @Column(name="Comment")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment() {
        super();
    }

    public static List<Comment> getComments() {
        return new Select().from(Comment.class).execute();
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment;
    }
}
