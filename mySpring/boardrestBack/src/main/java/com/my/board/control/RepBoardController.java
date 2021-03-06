package com.my.board.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.board.dao.RepBoardDAOInterface;
import com.my.board.service.RepBoardService;
import com.my.board.vo.RepBoard;
import com.my.customer.vo.Customer;
import com.my.dto.PageDTO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

import net.coobird.thumbnailator.Thumbnailator;

//@Controller
@RestController
@RequestMapping("board/*")
public class RepBoardController {
	@Autowired
	private RepBoardService service;

	@Autowired
	private RepBoardDAOInterface dao;

	private Logger logger = Logger.getLogger(this.getClass());

	@GetMapping(value= {"/list", "/list/{currentPage}"})
	public Object list(@PathVariable Optional<Integer> currentPage) {
		try {
			/*List<RepBoard> list;
			if(currentPage.isPresent()) { //currentPage?????? ?????? ??????
				int cp = currentPage.get();
				list = service.findAll(cp);
			}else { //?????? ?????? ??????(null??? ??????)
				list = service.findAll();
			}
			return list;
			*/
			PageDTO<RepBoard> pageDTO;
			if(currentPage.isPresent()) { //currentPage?????? ?????? ??????
				int cp = currentPage.get();
				pageDTO = service.findAll(cp);
			}else { //?????? ?????? ??????(null??? ??????)
				pageDTO = service.findAll();
			}
			return pageDTO;
		}catch(FindException e) {
			Map<String, Object> returnMap = new HashMap<>();
			returnMap.put("msg", e.getMessage());
			returnMap.put("status", 0);
			return returnMap;
		}
	}


//	@GetMapping("board/info")
//	public RepBoard info(HttpServletRequest request) throws FindException {

	@GetMapping("/{boardNo}")
	public Object info(@PathVariable(name = "boardNo") int no) throws FindException {
		try {
			RepBoard rb = dao.findByNo(no);
			return rb;
		} catch (FindException e) {
			e.printStackTrace();
			throw new FindException("???????????? ??? ???????????????");
		}
	}

//	@GetMapping("board/modify")
//	@ResponseBody
//	public Object modify(HttpServletRequest request) throws ModifyException, FindException {
	@PutMapping("/{boardNo}")
	public Object modify(@PathVariable int boardNo, @RequestBody RepBoard repboard, HttpServletRequest request)
			throws ModifyException {
		String boardContent = repboard.getBoardContent();

		Map<String, Object> returnMap = new HashMap<>();

		try {
			RepBoard rb = dao.findByNo(boardNo);
			rb.setBoardContent(boardContent);
			logger.error("boardContent=" + repboard.getBoardContent());
			dao.modify(rb);
			returnMap.put("msg", "?????? ??????");
			returnMap.put("status", 1);
		} catch (FindException e) {
			returnMap.put("msg", e.getMessage());
			returnMap.put("status", 0);
		}
		System.out.println(returnMap);
		return returnMap;
	}

	@PostMapping("/reply")
	public Object add(@RequestBody RepBoard repBoard) {
	
		Map<String, Object> returnMap = new HashMap<>();

		try {
			dao.add(repBoard);
			returnMap.put("msg", "?????? ??????");
			returnMap.put("status", 1);
		} catch (Exception e) {
			returnMap.put("msg", e.getMessage());
			returnMap.put("status", 0);
		}
		return returnMap;
	}

//	@GetMapping("board/remove")
//	@ResponseBody
//	public Object remove(HttpServletRequest request) throws RemoveException {
	@DeleteMapping("/{boardNo}")
	public Object remove(@PathVariable int boardNo) {
		Map<String, Object> returnMap = new HashMap<>();

		try {
			dao.remove(boardNo);
			returnMap.put("msg", "?????? ??????");
			returnMap.put("status", 1);
		} catch (RemoveException e) {
			returnMap.put("msg", e.getMessage());
			returnMap.put("status", 0);
		}

		return returnMap;
	}

	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestPart(required = false) List<MultipartFile> letterFiles,
			@RequestPart(required = false) MultipartFile imageFile, RepBoard repBoard) {
		logger.info("????????????????????? title=" + repBoard.getBoardTitle() + ", content=" + repBoard.getBoardContent());
		logger.info("letterFiles.size()=" + letterFiles.size());
		logger.info("imageFile.getSize()=" + imageFile.getSize());
		// ??????????????? DB??? ??????

		try {
			Customer c = new Customer();
			c.setId("id1");
			repBoard.setBoardC(c);
			service.write(repBoard);
		} catch (AddException e1) {
			e1.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// ?????? ????????????
		String saveDirectory = "c:\\files";
		if (!new File(saveDirectory).exists()) {
			logger.info("????????? ??????????????????");
			new File(saveDirectory).mkdirs();
		}

		int wroteBoardNo = repBoard.getBoardNo(); // ????????? ?????? ?????????

		// ????????? ????????? letterfiles ????????????
		for (MultipartFile mf : letterFiles) {
			if (letterFiles.size() == 0) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String letterFileName = mf.getOriginalFilename();
			logger.info("letterfile ????????????:" + letterFileName + " ????????????: " + mf.getSize());
			String savedLetterFileName = wroteBoardNo + "_letter_" + UUID.randomUUID() + "_" + letterFileName;
			File letterFile = new File(saveDirectory, savedLetterFileName);

			try {
				FileCopyUtils.copy(mf.getBytes(), letterFile);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		// ??????????????? ????????????
		String imageFileName = imageFile.getOriginalFilename(); // ?????????????????????????????????
		logger.info("????????? ????????????:" + imageFileName + " ????????????: " + imageFile.getSize());
		// ????????? ??????????????? ???????????? ex) ?????????_image_XXXX_????????????
		String imagefileName = wroteBoardNo + "_image_" + UUID.randomUUID() + "_" + imageFileName;
		// ????????????

		File thumbnailFile = null;

		File savedImagefile = new File(saveDirectory, imagefileName);
		try {
			FileCopyUtils.copy(imageFile.getBytes(), savedImagefile);
			logger.info("????????? ????????????:" + savedImagefile.getAbsolutePath());
			// ???????????? ??????
			String contentType = imageFile.getContentType();
			if (!contentType.contains("image/")) { // ???????????????????????? ?????? ??????
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

			// ?????????????????? ?????? ?????????????????? ??????
			String thumbnailName = "s_" + wroteBoardNo + "_" + UUID.randomUUID() + imageFileName; // ????????? ????????????
																									// s_?????????_XXXX_????????????
			thumbnailFile = new File(saveDirectory, thumbnailName);
			FileOutputStream thumbnailOS;
			thumbnailOS = new FileOutputStream(thumbnailFile);
			InputStream imageFileIS = imageFile.getInputStream();
			int width = 100;
			int height = 100;
			Thumbnailator.createThumbnail(imageFileIS, thumbnailOS, width, height);
			logger.info("??????????????? ??????:" + thumbnailFile.getAbsolutePath() + ", ??????????????? ??????:" + thumbnailFile.length());

		} catch (IOException e2) {
			e2.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// ????????? ???????????????????????????
		if (thumbnailFile != null) {
			try {
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set(HttpHeaders.CONTENT_LENGTH, thumbnailFile.length() + "");
				responseHeaders.set(HttpHeaders.CONTENT_TYPE, Files.probeContentType(thumbnailFile.toPath()));
				responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
						"inline; filename=" + URLEncoder.encode("a", "UTF-8"));
				logger.info("??????????????? ????????????");
				return new ResponseEntity<>(FileCopyUtils.copyToByteArray(thumbnailFile), responseHeaders,
						HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/download")
	public ResponseEntity<Resource> download(String fileName) throws UnsupportedEncodingException {
		logger.info("???????????? ????????????");
		// ?????? ????????????
		String saveDirectory = "c:\\files";

		// HttpHeaders : ??????/??????????????? API
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream;charset=UTF-8");
		// ??????????????? ???????????? ??????
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		// Resource : ??????(??????, URL)??? API
		// ??????????????? ????????? ?????? ?????? ??????
		File f = new File(saveDirectory, fileName);
		Resource resource = new FileSystemResource(f);
		try {
			logger.info("??????????????????: " + resource.getFilename());
			logger.info("????????????resource.contentLength()=" + resource.contentLength());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResponseEntity<Resource> responseEntity = new ResponseEntity<>(resource, headers, HttpStatus.OK);
		return responseEntity;
	}

}
