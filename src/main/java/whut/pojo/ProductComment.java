package whut.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * 商品评论
 * @author wangql
 *
 */
public class ProductComment implements Serializable{
    private Integer commentId; //评论ID

    private Integer productId;  //商品ID

    private Integer orderId; //订单ID

    private Integer userId;  //用户ID

    private String reply;  //回复评论

    private String content;  //评论内容
    
    private String secondContent; //第二次评论内容

    private Date commentTime;  //评论时间

    private Byte auditStatus;  //审核状态

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSecondContent() {
        return secondContent;
    }

    public void setSecondContent(String secondContent) {
        this.secondContent = secondContent == null ? null : secondContent.trim();
    }
    
    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Byte getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
    }
}