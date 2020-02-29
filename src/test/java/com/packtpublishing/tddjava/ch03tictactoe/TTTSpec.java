package com.packtpublishing.tddjava.ch03tictactoe;

import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Johnson
 * @date 2020/2/29 11:05
 */
public class TTTSpec {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private TicTacToe ticTacToe;
    private TicTacToeCollection collection;
    private TicTacToeBean bean;
    private MongoCollection mongoCollection;

    @Before
    public final void before() {
        ticTacToe = new TicTacToe();
        collection = mock(TicTacToeCollection.class);

        bean = new TicTacToeBean(3, 2, 1, 'Y');
//        mongoCollection = mock(MongoCollection.class);

        doReturn(true).when(collection).saveMove(any(TicTacToeBean.class));

        ticTacToe.setTicTacToeCollection(collection);

    }

    @Test
    public void whenPlayThenSaveMoveInvoked() {
        TicTacToeBean bean = new TicTacToeBean(1, 1, 1, 'X');
        ticTacToe.play(bean.getX(), bean.getY());
        verify(collection, times(1)).saveMove(bean);
    }

    @Test
    public void whenPlayAndSaveReturnsFlaseThenException() {
        doReturn(false).when(collection).saveMove(any(TicTacToeBean.class));
        TicTacToeBean move = new TicTacToeBean(1, 1, 1, 'Y');
        ticTacToe.play(move.getX(), move.getY());
    }
}
