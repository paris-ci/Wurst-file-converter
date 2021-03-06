/*
 * Copyright � 2015 | Alexander01998 | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.wurst_client.v1_7_keybind_converter.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import tk.wurst_client.v1_7_keybind_converter.Main;

public class FolderPathMenu extends Menu
{
	private JLabel pathLabel;
	
	public FolderPathMenu(Menu parent)
	{
		super("Wurst folder path", parent);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Main.instance.path = System.getenv("APPDATA") + "\\.minecraft\\wurst";
		if(!new File(Main.instance.path).exists())
			Main.instance.path = "";
		nextButtonEnabled = new File(Main.instance.path).exists();
		JLabel header = new JLabel("<html>"
			+ "<body width=512>"
			+ "<center>"
			+ "<p>This is where your keybinds are stored.</p>", SwingConstants.CENTER);
		header.setAlignmentX(CENTER_ALIGNMENT);
		add(header);
		add(Box.createVerticalStrut(20));
		pathLabel = new JLabel("Current path: " + Main.instance.path, SwingConstants.CENTER);
		pathLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(pathLabel);
		JButton browse = new JButton("Browse");
		browse.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(fileChooser.showOpenDialog(Main.instance) == JFileChooser.APPROVE_OPTION)
				{
					Main.instance.path = fileChooser.getSelectedFile().getAbsolutePath();
					pathLabel.setText("Current path: " + Main.instance.path);
				}
				setNextButtonEnabled(new File(Main.instance.path).exists());
			}
		});
		browse.setAlignmentX(CENTER_ALIGNMENT);
		add(browse);
	}
	
	@Override
	public void showNextMenu()
	{
		showMenu(new ProgressMenu());
	}
}
