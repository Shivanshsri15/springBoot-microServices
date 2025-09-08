package com.threadly.product.dao;

import com.threadly.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    String message;
    Object data;
}
