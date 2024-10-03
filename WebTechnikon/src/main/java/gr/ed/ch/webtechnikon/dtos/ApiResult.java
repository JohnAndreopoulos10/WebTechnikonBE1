package gr.ed.ch.webtechnikon.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents a standardized response structure for API results.
 * It includes fields for a message, data, success status, 
 * pagination information and a timestamp(commeci commeca).
 * Provides static methods for creating success and failure responses.
 * @param <T> the type of data contained in the response
 * @author Johnnie
 */
@Data
@NoArgsConstructor 
public class ApiResult<T> {
    private String message;
    private T data;
    private boolean success;
    private int totalItems = 0;  
    private int totalPages = 0;  
  //  private long timestamp = System.currentTimeMillis(); 

    public ApiResult(String message, T data, boolean success, int totalItems, int totalPages) {
        this.message = message;
        this.data = data;
        this.success = success;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    //    this.timestamp = System.currentTimeMillis(); 
    }

    public static <T> ApiResult<T> success(String message, T data, int totalItems, int totalPages) {
        return new ApiResult<>(message, data, true, totalItems, totalPages);
    }

    public static <T> ApiResult<T> failure(String message) {
        return new ApiResult<>(message, null, false, 0, 0);
    }
}
