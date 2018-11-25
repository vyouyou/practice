package youyou.redisprj.net.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qisy01
 * @create 18-11-21
 * @since 1.0.0
 */
@Setter
@Getter
public class ArticleRequest {
    private Integer articleId;

    private String articleName;

    private String bookName;
}
