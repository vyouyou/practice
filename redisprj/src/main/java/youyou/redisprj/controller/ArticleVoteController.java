package youyou.redisprj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import youyou.redisprj.net.request.ArticleRequest;
import youyou.redisprj.net.request.VoteRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author qisy01
 * @create 18-11-21
 * @since 1.0.0
 */
@RestController
@RequestMapping("/article")
public class ArticleVoteController {

    private static final String ARTICLE_KEY = "article:article:";

    private static final String READER_KEY = "article:readerList";

    private static final String ARTICLE_ORDER = "article:order:";

    private static final Integer ONE_WEEK_SECONDS = 7 * 24 * 3600;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "投票")
    @PostMapping("/vote")
    public String vote(@RequestBody VoteRequest voteRequest) {
        String readerKey = READER_KEY + voteRequest.getArticleId();
        String articleKey = ARTICLE_KEY + voteRequest.getArticleId();
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add(readerKey, voteRequest.getUserId());
        Integer votes = setOperations.members(readerKey).size();
        redisTemplate.opsForHash().put(articleKey, "vote", votes);
        redisTemplate.opsForZSet().incrementScore(ARTICLE_ORDER, articleKey, votes);
        return "vote success";
    }

    @ApiOperation(value = "增加作者")
    @PostMapping("/add-article")
    public String addArticle(@RequestBody ArticleRequest articleRequest) {
        Map<String, Object> map = mapper.convertValue(articleRequest, Map.class);
        map.put("vote", 1);
        Integer articleId = articleRequest.getArticleId();
        redisTemplate.opsForHash().putAll(ARTICLE_KEY + articleId, map);
        String readerKey = READER_KEY + articleId;
        redisTemplate.opsForSet().add(readerKey, articleId);
        redisTemplate.expire(readerKey, ONE_WEEK_SECONDS, TimeUnit.SECONDS);
        redisTemplate.opsForZSet().add(ARTICLE_ORDER, articleId, 1);
        return "success";
    }
}
