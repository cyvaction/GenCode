package com.cheney.web.controller.linuxbash;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Moudle: GenSeniorCodeController
 * @version:v1.0
 * @Description: LinuxBash高级代码生成器控制入口
 * @author: xuyushuai
 * @date: 2015年6月6日 上午11:12:57
 *
 */
@RestController
@RequestMapping("/gencode/linuxbash")
public class GenSeniorCodeController {

	@RequestMapping("/genSenior")
	public Map<String,String> genBasic(String type) {
		Map<String,String> codeMap = new HashMap<String,String>();
		codeMap.put("code", "建设中...");
		codeMap.put("respMsg", "Y");
		return codeMap;
	}
}