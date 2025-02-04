package it.unibo.michelito.util.hitbox;

import it.unibo.michelito.util.position.Position;

/**
 * a factory implementation.
 */
public final class HitBoxFactoryImpl implements HitBoxFactory {

    private abstract static class HitBoxImpl implements HitBox {

        private final Position center;

        HitBoxImpl(final Position center) {
            this.center = center;
        }

        @Override
        public Position getCenter() {
            return this.center;
        }

        @Override
        public HitBox getHitBox() {
            return this;
        }

        @Override
        public boolean collision(final HitBox hitBox) {
            return hitBox.inner(topRight()) || hitBox.inner(downleft())
                                            || hitBox.inner(new Position(downleft().x(), topRight().y()))
                                            || hitBox.inner(new Position(topRight().x(), downleft().y()));
        }

        @Override
        public boolean inner(final Position position) {
            return topRight().x() >= position.x() && topRight().y() <= position.y()
                                                  && downleft().x() <= position.x()
                                                  && downleft().y() >= position.y();
        }

        abstract Position topRight();

        abstract Position downleft();
    }


    @Override
    public HitBox squareHitBox(final Position position) {
        return new HitBoxImpl(position) {
            @Override
            Position topRight() {
                return new Position(position.x() + 2, position.y() - 2);
            }

            @Override
            Position downleft() {
                return new Position(position.x() - 2, position.y() + 2);
            }
        };
    }

    @Override
    public HitBox entityeHitBox(final Position position) {
        return new HitBoxImpl(position) {
            @Override
            Position topRight() {
                return new Position(position.x() + 1, position.y() - 1.5);
            }

            @Override
            Position downleft() {
                return new Position(position.x() - 1, position.y() + 1.5);
            }
        };
    }
}
