package com.mikeburke106.mines.basic.model;

import com.mikeburke106.mines.api.model.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

/**
 * Basic implementation of a position provider that returns position values at random
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public class RandomPositionProvider extends UniquePositionProvider {
    private final Random random;

    public RandomPositionProvider(List<Position> availablePositions, Random random) {
        super(availablePositions);
        this.random = random;
    }

    @Override
    public Position nextPosition() throws NoPositionsAvailableException {
        if (availablePositions.isEmpty()) {
            throw new NoPositionsAvailableException();
        }

        return take(random.nextInt(availablePositions.size()));
    }

    @Override
    public String toString() {
        return "RandomPositionProvider{" +
                "availablePositions=\n" + Arrays.toString(availablePositions.toArray()) +
                ", random=" + random +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RandomPositionProvider that = (RandomPositionProvider) o;

        return random.equals(that.random) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return random.hashCode() * 31 * super.hashCode();
    }

    public static class Factory implements Position.Provider.Factory {
        private Random random;

        public Factory() {
            this(new Random());
        }

        public Factory(Random random) {
            this.random = random;
        }

        @Override
        public Position.Provider newInstance(Position.Pool positionPool) {
            List<Position> avaialablePositions = new ArrayList<>(positionPool.size());

            for (Position position : positionPool) {
                avaialablePositions.add(position);
            }

            return new RandomPositionProvider(avaialablePositions, random);
        }
    }
}
