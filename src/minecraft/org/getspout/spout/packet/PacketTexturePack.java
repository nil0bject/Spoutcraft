package org.getspout.spout.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.File;

import net.minecraft.src.RenderEngine;

import org.getspout.spout.client.SpoutClient;
import org.getspout.spout.io.FileUtil;
import org.getspout.spout.texture.TexturePackAction;
import org.getspout.spout.io.Download;
import org.getspout.spout.io.FileDownloadThread;

public class PacketTexturePack implements SpoutPacket{
	private static byte[] downloadBuffer = new byte[16384];
	private String url;
	private long expectedCRC;
	
	public PacketTexturePack(){
		
	}
	
	public PacketTexturePack(String url, long expectedCRC) {
		this.url = url;
		this.expectedCRC = expectedCRC;
	}

	@Override
	public int getNumBytes() {
		return PacketUtil.getNumBytes(url) + 4;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		url = PacketUtil.readString(input, 256);
		expectedCRC = input.readLong();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(output, url);
		output.writeLong(expectedCRC);
	}

	@Override
	public void run(int PlayerId) {
		if (url.equals("[none]")) {
			if (SpoutClient.getHandle().renderEngine.oldPack != null) {
				RenderEngine rengine = SpoutClient.getHandle().renderEngine;
				rengine.texturePack.setTexturePack(rengine.oldPack);
				rengine.oldPack = null;
				rengine.refreshTextures();
			}
		} else {
			String fileName = FileUtil.getFileName(url);
			if (!FileUtil.isZippedFile(fileName)) {
				System.out.println("Rejecting Invalid Texture Pack: " + fileName);
				return;
			}
			File texturePack = new File(FileUtil.getTexturePackDirectory(), fileName);
			if (FileUtil.getCRC(texturePack, downloadBuffer) != expectedCRC && expectedCRC != 0) {
				texturePack.delete();
			}
			Download download = new Download(fileName, FileUtil.getTexturePackDirectory(), url, new TexturePackAction(fileName, FileUtil.getTexturePackDirectory()));
			FileDownloadThread.getInstance().addToDownloadQueue(download);
		}
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketTexturePack;
	}
	
	@Override
	public int getVersion() {
		return 1;
	}

	@Override
	public void failure(int playerId) {
		
	}

}
