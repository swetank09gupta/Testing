package com.huntingcube;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewClass {

    public static void main(String[] args) {
        CompletableFuture future = new CompletableFuture();
        future.supplyAsync(() -> "Hello").thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "World"));

        System.out.println(future);

        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);
        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));
        System.out.println(combined);
    }
}
