package org.yoshiori;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ProcTest {

	@Test
	public void 処理が普通に行える() throws Throwable{
		final List<String> list = new ArrayList<String>();
		Proc.retry(3,new Runnable() {
			
			@Override
			public void run() {
				list.add("hoge");
			}
		});
		assertThat(list.size(), is(1));
		assertThat(list.get(0), is("hoge"));
	}

	@Test
	public void エラーが発生しても処理が普通に行える_非チェック例外() throws Throwable{
		final List<String> list = new ArrayList<String>();
		Proc.retry(3,new Runnable() {
			int i = 0;
			@Override
			public void run() throws Throwable {
				if(i < 2){
					i++;
					throw new RuntimeException();
				}
				list.add("hoge");
			}
		});
		assertThat(list.size(), is(1));
		assertThat(list.get(0), is("hoge"));
	}

	@Test
	public void エラーが発生しても処理が普通に行える_チェック例外() throws Throwable{
		final List<String> list = new ArrayList<String>();
		Proc.retry(3,new Runnable() {
			int i = 0;
			@Override
			public void run() throws Throwable {
				if(i < 2){
					i++;
					throw new IOException();
				}
				list.add("hoge");
			}
		});
		assertThat(list.size(), is(1));
		assertThat(list.get(0), is("hoge"));
	}

	@Test
	public void エラーを指定してそれが発生しても処理が普通に行える_チェック例外() throws Throwable{
		final List<String> list = new ArrayList<String>();
		Proc.retry(3,new Runnable() {
			int i = 0;
			@Override
			public void run() throws Throwable {
				if(i < 2){
					i++;
					throw new IOException();
				}
				list.add("hoge");
			}
		},IOException.class);
		assertThat(list.size(), is(1));
		assertThat(list.get(0), is("hoge"));
	}

	@Test(expected=IOException.class)
	public void 指定したエラー以外の時は処理が止まる() throws Throwable{
		final List<String> list = new ArrayList<String>();
		Proc.retry(3,new Runnable() {
			int i = 0;
			@Override
			public void run() throws Throwable {
				if(i < 2){
					i++;
					throw new IOException();
				}
				list.add("hoge");
			}
		},RuntimeException.class);
	}

	@Test
	public void 指定した時間waitを置いて処理を実行する() throws Throwable{
		final List<String> list = new ArrayList<String>();
		long startTime = System.currentTimeMillis();
		Proc.retry(3,new Runnable() {
			int i = 0;
			@Override
			public void run() throws Throwable {
				if(i < 2){
					i++;
					throw new IOException();
				}
				list.add("hoge");
			}
		},5 * 1000);
		long endTime = System.currentTimeMillis();
		assertThat(endTime - startTime, greaterThanOrEqualTo(10 * 1000L));
	}

	@Test
	public void 指定した時間waitを置いて処理を実行する_キャッチする例外を指定() throws Throwable{
		final List<String> list = new ArrayList<String>();
		long startTime = System.currentTimeMillis();
		Proc.retry(3,new Runnable() {
			int i = 0;
			@Override
			public void run() throws Throwable {
				if(i < 2){
					i++;
					throw new IOException();
				}
				list.add("hoge");
			}
		},5 * 1000, IOException.class);
		long endTime = System.currentTimeMillis();
		assertThat(endTime - startTime, greaterThanOrEqualTo(10 * 1000L));
	}

	@Test(expected=IllegalArgumentException.class)
	public void キャッチする例外にnullを指定していたらIllegalArgumentExceptionを発生させる() throws Throwable{
		final List<String> list = new ArrayList<String>();
		Proc.retry(3,new Runnable() {
			int i = 0;
			@Override
			public void run() throws Throwable {
				if(i < 2){
					i++;
					throw new IOException();
				}
				list.add("hoge");
			}
		},null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void sleeptimeにマイナスを指定していたらIllegalArgumentExceptionを発生させる() throws Throwable{
		final List<String> list = new ArrayList<String>();
		Proc.retry(3,new Runnable() {
			int i = 0;
			@Override
			public void run() throws Throwable {
				if(i < 2){
					i++;
					throw new IOException();
				}
				list.add("hoge");
			}
		},-1L);
	}
}
