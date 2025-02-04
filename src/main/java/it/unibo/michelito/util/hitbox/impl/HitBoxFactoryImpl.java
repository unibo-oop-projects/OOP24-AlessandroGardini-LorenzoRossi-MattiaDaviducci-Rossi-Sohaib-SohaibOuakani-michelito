package it.unibo.michelito.util.hitbox.impl;

import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.api.HitBoxFactory;



/**
 * a hitbox is a feature of every maze object that rappresent his physicality.
 */
public final class HitBoxFactoryImpl implements HitBoxFactory {
    private static final double X_DIMENSION_ENTITY = 1;
    private static final double Y_DIMENSION_ENTITY = 1.5;
    private static final double DIMENSION_SQUARE = 2;

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
                return new Position(position.x() + DIMENSION_SQUARE, position.y() - DIMENSION_SQUARE);
            }

            @Override
            Position downleft() {
                return new Position(position.x() - DIMENSION_SQUARE, position.y() + DIMENSION_SQUARE);
            }
        };
    }

    @Override
    public HitBox entityeHitBox(final Position position) {

        return new HitBoxImpl(position) {
            @Override
            Position topRight() {
                return new Position(position.x() + X_DIMENSION_ENTITY, position.y() - Y_DIMENSION_ENTITY);
            }

            @Override
            Position downleft() {
                return new Position(position.x() - X_DIMENSION_ENTITY, position.y() + Y_DIMENSION_ENTITY);
            }
        };
    }
}
