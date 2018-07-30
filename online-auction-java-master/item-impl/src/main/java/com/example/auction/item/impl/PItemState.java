package com.example.auction.item.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.lightbend.lagom.serialization.Jsonable;
import lombok.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Value
public class PItemState implements Jsonable {

    private final Optional<PItem> item;

    @JsonCreator
    private PItemState(Optional<PItem> item) {
        this.item = item;
    }

    public static PItemState empty() {
        return new PItemState(Optional.empty());
    }

    public static PItemState create(PItem item) {
        return new PItemState(Optional.of(item));
    }

    public PItemState start(Instant startTime) {
        return update(i -> i.start(startTime));
    }

    public PItemState end(Optional<UUID> winner, int price) {
        return update(i -> i.end(winner, price));
    }

    public PItemState updatePrice(int price) {
        return update(i -> i.updatePrice(price));
    }

    public PItemState updateDetails(PItemData details) {
        return update(i -> i.withDetails(details));
    }

    public PItemState cancel() {
        return update(i -> i.cancel());
    }

    public PItemStatus getStatus() {
        return item.map(PItem::getStatus).orElse(PItemStatus.NOT_CREATED);
    }


    // ----------------------------------------------------------------------------------------
    private PItemState update(Function<PItem, PItem> updateFunction) {
        assert item.isPresent();
        return new PItemState(item.map(updateFunction));
    }

}
