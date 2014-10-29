package storyboards.diagram.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;
import storyboards.diagram.providers.StoryboardsElementTypes;

/**
 * @generated
 */
public class StoryboardsPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createStoryboards1Group());
	}

	/**
	 * Creates "storyboards" palette tool group
	 * 
	 * @generated
	 */
	private PaletteContainer createStoryboards1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(Messages.Storyboards1Group_title);
		paletteContainer.setId("createStoryboards1Group"); //$NON-NLS-1$
		paletteContainer.add(createStoryboard1CreationTool());
		paletteContainer.add(createProperty2CreationTool());
		paletteContainer.add(createAction3CreationTool());
		paletteContainer.add(createPath4CreationTool());
		paletteContainer.add(createCondition5CreationTool());
		paletteContainer.add(createStartNode6CreationTool());
		paletteContainer.add(createEndNode7CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createStoryboard1CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Storyboard1CreationTool_title,
				Messages.Storyboard1CreationTool_desc,
				Collections.singletonList(StoryboardsElementTypes.Storyboard_2012));
		entry.setId("createStoryboard1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj16/Storyboard.gif")); //$NON-NLS-1$
		entry.setLargeIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj32/Storyboard.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProperty2CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Property2CreationTool_title,
				Messages.Property2CreationTool_desc, Collections.singletonList(StoryboardsElementTypes.Property_2008));
		entry.setId("createProperty2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj16/Property.gif")); //$NON-NLS-1$
		entry.setLargeIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj32/Property.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAction3CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Action3CreationTool_title,
				Messages.Action3CreationTool_desc, Collections.singletonList(StoryboardsElementTypes.Action_2006));
		entry.setId("createAction3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj16/Action.gif")); //$NON-NLS-1$
		entry.setLargeIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj32/Action.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPath4CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(4);
		types.add(StoryboardsElementTypes.ConditionPath_4001);
		types.add(StoryboardsElementTypes.ActionProperties_4002);
		types.add(StoryboardsElementTypes.StartNodeFirstNode_4004);
		types.add(StoryboardsElementTypes.ActionNodeNextNode_4006);
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.Path4CreationTool_title,
				Messages.Path4CreationTool_desc, types);
		entry.setId("createPath4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj16/Path.gif")); //$NON-NLS-1$
		entry.setLargeIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj32/Path.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCondition5CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Condition5CreationTool_title,
				Messages.Condition5CreationTool_desc, Collections.singletonList(StoryboardsElementTypes.Condition_2010));
		entry.setId("createCondition5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj16/Condition.gif")); //$NON-NLS-1$
		entry.setLargeIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj32/Condition.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createStartNode6CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.StartNode6CreationTool_title,
				Messages.StartNode6CreationTool_desc, Collections.singletonList(StoryboardsElementTypes.StartNode_2007));
		entry.setId("createStartNode6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj16/StartNode.gif")); //$NON-NLS-1$
		entry.setLargeIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj32/StartNode.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEndNode7CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.EndNode7CreationTool_title,
				Messages.EndNode7CreationTool_desc, Collections.singletonList(StoryboardsElementTypes.EndNode_2009));
		entry.setId("createEndNode7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj16/EndNode.gif")); //$NON-NLS-1$
		entry.setLargeIcon(StoryboardsDiagramEditorPlugin
				.findImageDescriptor("/Storyboards.edit/icons/full/obj32/EndNode.gif")); //$NON-NLS-1$
		return entry;
	}
}
