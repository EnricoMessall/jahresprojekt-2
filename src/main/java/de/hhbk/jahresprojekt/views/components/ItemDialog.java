package de.hhbk.jahresprojekt.views.components;

import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.ItemRepository;
import de.hhbk.jahresprojekt.model.Item;
import de.hhbk.jahresprojekt.model.builder.ItemBuilder;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;

public class ItemDialog extends Dialog<Item>{
    VBox vBox;
    TextField comment, amount;

    public ItemDialog() {
        super(Item.class);
    }

    @Override
    Node initView() {
        vBox = new VBox();
        comment = new TextField();
        amount = new TextField();

        vBox.getChildren().add(new Label("Comment"));
        vBox.getChildren().add(comment);
        vBox.getChildren().add(new Label("Amount"));
        vBox.getChildren().add(amount);
        return vBox;
    }

    @Override
    public void copyFrom(Item object) {
        if(object == null) return;
        comment.setText(object.getComment());
        amount.setText(String.valueOf(object.getValue()));
    }

    @Override
    Item getChangedObject() {
        int value = StringUtils.isNotBlank(amount.getText()) ? Integer.parseInt(amount.getText()) : 0;
        Item item = ItemBuilder.anItem().withComment(comment.getText()).withValue(value).build();
        RepositoryContainer.get(ItemRepository.class).save(item);
        return item;
    }
}
