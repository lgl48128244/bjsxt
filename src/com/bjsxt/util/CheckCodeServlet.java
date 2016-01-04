package com.bjsxt.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static int WIDTH = 80;//设置图片的宽度

	private static int HEIGHT = 40;//设置图片的高度

	private static final String SERVER_CODE = "randomCode";

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取操作类型
		String opertype = req.getParameter("opertype");
		//获取请求方式
		String method = req.getMethod().toLowerCase();
		if (opertype != null && "generateCode".equals(opertype) && "get".equals(method)) {//生成验证码
			doGenerateCode(req, resp);
		} else if (opertype != null && "validateCode".equals(opertype) && "post".equals(method)) {//验证验证码
			doValidateCode(req, resp);
		} else {
			SxtLogger.logger.error("(找不到指定的方法 或者是请求方式不正确)" + opertype + "---->" + method);
		}
	}

	/**
	 * 验证验证码
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void doValidateCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取客户端传递的验证码
		String clientCode = req.getParameter("clientCode");
		//真正的验证码
		String serverCode = (String) req.getSession().getAttribute(SERVER_CODE);
		//进行比较
		resp.setContentType("text/html;charset=utf-8");
		if (clientCode != null && serverCode != null && clientCode.toUpperCase().equals(serverCode.toUpperCase())) {
			resp.getWriter().print("true");
		} else {
			resp.getWriter().print("false");
		}
	}

	/**
	 * 生成注册码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doGenerateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		response.setContentType("image/jpeg");
		ServletOutputStream sos = response.getOutputStream();

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		//生成验证码
		char[] rands = generateCheckCode();
		//画背景图
		drawBackground(g);
		//画验证码
		drawRands(g, rands);
		//		
		g.dispose();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		byte[] buf = bos.toByteArray();
		response.setContentLength(buf.length);
		sos.write(buf);
		bos.close();
		sos.close();
		session.setAttribute(SERVER_CODE, new String(rands));

	}

	private void drawBackground(Graphics g) {
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for (int i = 0; i < 120; i++) {
			int x = (int) (Math.random() * WIDTH);
			int y = (int) (Math.random() * HEIGHT);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, 1, 0);
		}
	}

	private void drawRands(Graphics g, char[] rands) {
		// g.setColor(Color.BLUE);
		Random random = new Random();
		int red = random.nextInt(110);
		int green = random.nextInt(50);
		int blue = random.nextInt(50);
		g.setColor(new Color(red, green, blue));
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));
		g.drawString("" + rands[0], 1, 17);
		g.drawString("" + rands[1], 16, 15);
		g.drawString("" + rands[2], 31, 18);
		g.drawString("" + rands[3], 46, 16);
	}

	private char[] generateCheckCode() {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] rands = new char[4];
		for (int i = 0; i < 4; i++) {
			int rand = (int) (Math.random() * 36);
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}