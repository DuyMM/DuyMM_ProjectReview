package com.example.maimanhduy.rbook.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.fragments.SettingFragment;

public class ReadBookActivity extends AppCompatActivity implements SettingFragment.OnCallBackFormSettingFragment {
    private ProgressBar mProgressBar;
    private TextView mTvReadBook;
    private ScrollView mScrollView;
    private int max;
    private Fragment fragment;
    private float textSize = 15;
    private float lineSpacing = 0;
    private ImageView mImgShowSettingFragment;
    private ImageView mImgBackFormReadBook;
    private String[] mFont = {"fonts/Roboto-Medium.ttf",
            "fonts/ArimaMadurai-Medium.ttf",
            "fonts/BalooChettan-Regular.ttf",
            "fonts/Lemonada-Light.ttf",
            "fonts/OpenSans-CondLight.ttf"};
    private Typeface mTypeFace;
    private FragmentTransaction ft;
    private View mThisFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBarReadBook);
        mTvReadBook = (TextView) findViewById(R.id.tvReadBook);
        mScrollView = (ScrollView) findViewById(R.id.scrollViewReadBook);
        ft = getSupportFragmentManager().beginTransaction();
        fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentSetting);
        mThisFragment = findViewById(R.id.fragmentSetting);
        mThisFragment.setVisibility(View.INVISIBLE);

        //mProgressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorMain), PorterDuff.Mode.SRC_IN);
        // mProgressBar.setProgressBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorMain)));
        // mProgressBar.getThumb().setColorFilter(getResources().getColor(R.color.colorMain), PorterDuff.Mode.SRC_IN);
        mTvReadBook.setText("Prologue: Mở đầu\n" +
                "Chương 1: Mở đầu.\n" +
                "Ngày hôm ấy , tuyết đã rơi .\n" +
                "\n" +
                "Những vết máu tương tự như một bông hồng nhuộm đỏ tuyết trắng .\n" +
                "\n" +
                "Chùm thép được chất ở phía sau của một chiếc xe tải đổ sập ngay trước mắt tôi , tôi bị tóm lấy và hấp hối trước khi tôi nhận ra nó.\n" +
                "\n" +
                "Ờ , nếu như tôi mất đi thì cô ấy cũng sẽ không còn tồn tại nữa.\n" +
                "\n" +
                "Trong khi  buồn rầu bởi vì đồng thời mất cả tôi và Hiyuki , ý thức của tôi phai mờ và tan chảy vào trong bóng tối…\n" +
                "\n" +
                "◆◇◆◇\n" +
                "\n" +
                "Tôi sau đó tỉnh dậy trong một cỗ quan tài sang trọng , với hoa hồng khắp ở bên trong .\n" +
                "\n" +
                "“- Uwaah - ! ?”\n" +
                "\n" +
                "Theo bản năng hiểu biết này vì mý do nào đó . Tôi đặt tay lên nắp quan tài và đẩy nó nó mở với tất cả sức mạnh phần trên cơ thể của tôi.\n" +
                "\n" +
                "Thật bất ngờ vì nó mở khá dễ dàng và từ đó tôi chồm ra ngoài , và nhìn xung quanh một cách vội vã.\n" +
                "\n" +
                "-          Cái chỗ đếu nào đây ? Kể từ lúc tôi còn ở trong một  chiếc quan tài , điều đó có nghĩa là đây một nhà tang lễ hoặc hỏa táng đúng khổng nhỉ!? Nếu tôi tỉnh dậy khi họ bắt đầu hỏa thiêu thì chuyện đó đếu vui tí nào đâu !\n" +
                "\n" +
                "Tuy nhiên , trái với dự kiến của tôi đây là một lâu đài thời trung cổ . Nơi đây có giường vòm lá tráng lệ khổng lồ mà tôi không biết được kích thước nếu tôi tính nó trong tatami , nhưng dường như nó được sử dụng bởi dòng dõi hoàng tộc , nhìn chung thì nơi này khá rộng rãi , nhiều đồ đạc sang trọng được sắp xếp trong phòng ngủ này.\n" +
                "\n" +
                "“ Heh ? Nơi này là đâu ??\n" +
                "\n" +
                "Giọng nói mà tôi dùng để lẩm bẩm một mình trở nên cao kì lạ và rất dễ thương .\n" +
                "\n" +
                "Cũng rõ ràng những gì tôi đang mặc là một chiếc váy mượt ( ánh lên ) ánh sáng rực rỡ mà chủ yếu gồm màu đen và được trang trí bằng bông hồng đỏ cài ngực lộng lẫy .\n" +
                "\n" +
                "Ở bên trên đó nơi mà mái tóc dài đen huyền óng ánh như nhung treo xuống , mà chắc chắn là thuộc về tôi . Khi tôi nhìn lại xuống và nhìn vào chỗ đó . Tôi thấy ngực tôi có hơi phồng và vòng eo rất là mảnh mai tôi nghĩ rằng nó có thể dễ dàng bẻ gãy . và sau đó-\n" +
                "\n" +
                "“….Biến mắt”\n" +
                "\n" +
                " “-Ok…”\n" +
                "\n" +
                "Trong khi cố hiểu cái cảm giác mất mát ở đấy quần mình , Tôi cố để ý rằng ngón tay của tôi mảnh khảnh nhưng linh hoạt , giống như mấy đứa con nít . Cái biểu tượng của một người đàn ông mà tôi quen thuộc đã biến mất ,  thay thế vào chỗ đó là một cái khe lạ ( Trans : à vâng cái khe thần thánh =)) )\n" +
                "\n" +
                "Tôi nuốt lời nói của tôi ngay lập tức như tôi định nói từ “ Chiếm đóng “ trên phản xạ .\n" +
                "\n" +
                "“Xin thứ lỗi”\n" +
                "\n" +
                "Có một giọng chào đón tôi vào ngay giữa cái tình trạng làm tôi khó chịu này . Cánh cửa nặng nề đó là khoảng chừng 3x2 mét đã được đây và mở một cách dễ dàng , và một người đàn ông đẹp trai với đôi mắt và tóc vàng hấp dẫn bước vào phòng.\n" +
                "\n" +
                "Người này dường như mới chừng 20 tuổi và được ăn mặc giống bisshi với một bộ lễ phục; nó đủ để làm tôi phải ngạc nhiên. Hắn ta thật sự rất đẹp trai và nở ra một nụ cười rạng rỡ với tôi\n" +
                "\n" +
                "Không-không , tôi sẽ không đi qua “qua đường đó” . Cho tới bây giờ gã điển trai đang nổi nụ cười rạng rỡ đó , gần giống như là một thứ vũ khí . Nếu tôi là một cô gái đây là nơi tôi sẽ fall in love , huh… nhưng lúc này tôi lại là một cô gái! Như thế thì… tôi đang gặp một cơn khủng hoảng . ( Trans : vừa mới hồi sinh gặp trai đã tươm tướt :cry:)\n" +
                "\n" +
                "Suy nghĩ linh tinh , trước mặt tôi là một gã điển trai đặt tay phải lên ngực mình , và quỳ gối như một hiệp sĩ . Hắn ta cuối đầu mình xuống và đặt chân lên phía trước.\n" +
                "\n" +
                "“Hoàng thân công chúa đã tái sinh , nó thật sự là một niềm vui vô cùng tuyệt vời mà tôi có ,  Tenga , người đầu tiên chứng kiến điều đó , tất cả mọi người trong lãnh thổ này đã và đang hi vọng tận đáy lòng của họ đối với ngày hôm nay .”\n" +
                "\n" +
                "Một gã điển trai có những hình thức và nghi thức khác nhau Tôi đoán-   nhưng đợi một chút tôi vừa nhớ ra một cái gì đó , đột nhiên , lời nói đó tôi chắc chắc là mình không thể nghe nhầm được ; Tôi tự tiện nâng cao một giọng nói kích động.\n" +
                "\n" +
                "“ Tengai !!?”\n" +
                "\n" +
                "“…Hu-huh . Tôi cần phải làm điều gì , công chúa?”\n" +
                "\n" +
                "Hắn không trả lời câu hõi của tôi và có vẻ rất đáng ngờ , vì thế tôi chỉ về phía gã điển trai đó.\n" +
                "\n" +
                "“Kim Long [Naga Raja]??!”\n" +
                "\n" +
                "“ A ? Vâng , là tôi ?”\n" +
                "\n" +
                "Ngay lập tức , một cái gì đó giống như một cửa sổ pop-up từ menu trong suốt xuất hiện tại nơi tôi đã chỉ , thông tin hiển thị của nó che phủ gã điển trai . \n" +
                "\n" +
                "MP: 767,800(Human Form)/2,303,400(Half Dragon Form)/53,746,000(Dragon Form)\n" +
                "\n" +
                "Chủng tộc : Kim Long [Naga Raja]\n" +
                "\n" +
                "Tên : Tengai\n" +
                "\n" +
                "Người sở hữu : Hiyuki\n" +
                "\n" +
                "HP : 556,800 ( dưới hình dạng con người ) / 1,948,800 ( dưới hình dạng bán long) / 61,234,800 ( dạng rồng )\n" +
                "\n" +
                "MP: 767,800 ( dưới hình dạng con người ) / 2,303,400 ( dưới hình dạng bán long ) /53,746,000 ( dạng rồng )\n" +
                "\n" +
                "▼\n" +
                "\n" +
                "Kim Lonh [Naga Raja] ban đầu được xem như là boss cho một sự kiện của ‘Eternal Horizon Online’ , một trò chơi tôi đã chơi . Sau đó nó được phát hành như một SSR ( Siêu- siêu hiếm ) class item cho một giải thưởng Gacha trong một sự kiện giới hạn.\n" +
                "\n" +
                "Vào thời điểm đó tôi cũng đã cố gắng để có được nó , nhưng không phải dễ dàng chút nào ; tôi có được nó bằng cách trao đổi mội trong những tài sản quý giá của tôi và một lượng lớn item hiếm . Có lẽ nó một con quái vật level cực cao , thậm chí không tổng năm trong số chúng trên sever .\n" +
                "\n" +
                "Và sau đó tôi đặt tên con Kim Long [ Naga Raja ] mà tôi có với cái tên là Tengai nhưng , đừng nói với tôi nó chính là người này ?! Anh ta là con rồng đó ?!\n" +
                "\n" +
                "Trong khi tôi đang bối rối , tôi chỉ ngón tay vào bản thân mình , một cửa sổ pop-up bán trong suốt trên menu hiện lên theo cùng một cách .\n" +
                "\n" +
                "Chủng tộc : Công chúa Ma cà rồng [ Thiên chúa tổ tiên]\n" +
                "\n" +
                "Tên : Hiyuki\n" +
                "\n" +
                "Danh hiệu : Tenjoutenga [ Thiếu nữ yêu kiều của cõi thiên đường ]\n" +
                "\n" +
                "HP : 78,000\n" +
                "\n" +
                "MP :95,500\n" +
                "\n" +
                "▼\n" +
                "\n" +
                "Đây chắc chắc là tên nhân vật của tôi và trang thái trong “ Eternal Horizon Online’\n" +
                "\n" +
                "-----------------\n" +
                "\n" +
                "Cánh cửa mở trái đã được gõ cho lịch sự . Mặc một bộ trang phục maid , một người phụ nữ có tóc bạch kim óng ánh với đôi mắt lạnh giá và 3 cặp cánh bước vào phòng . Có một cảm giác quen thuôc từ người phụ đi đến hướng này . Tất nhiên tôi quan sát cô ấy.\n" +
                "\n" +
                "Cô gửi một cái gì về phía Tengai người đi đến trước đó và bây giờ đang quỳ gối , sau đó cô ấy đã nói với một giọng điệu hơi tôn trọng .\n" +
                "\n" +
                "“Tengai-dono , điều này thật đáng buồn rằng anh đã làm một chuyện thô lỗ ở lối ra vào trong phòng của một người phụ nũ . Tuy không có vấn đề gì , nhưng không nên cho kể lãnh đạo của 4 Vua Quỷ Thiên Giới không được hành động thô lỗ ?”\n" +
                "\n" +
                "“ …Uh , là lỗi của tôi Mikoto . Tôi cảm thấy ma thuật của công chúa đã thức tỉnh , vì thế tôi không thể cứ đứng yên..”\n" +
                "\n" +
                "Suy nghĩ rằng anh ta trả lời rất hối hận , nhưng không có vẻ gì là tiếc nuối cả , cô ấy lắc đầu bất lực . Sau đó một khi cô ấy quay về phía tôi , cô đứng thẳng để cho thấy chức tước của mình và cúi chào tôi .\n" +
                "\n" +
                "“ Công chúa , cô đã thức giấc , đối với thần , đó là một niềm vui không thể đạt được thậm chí còn tuyệt vời hơn bất kí điều gì .”\n" +
                "\n" +
                "Đúng như tôi nghĩ  , đó chính là Mikoto hử .\n" +
                "\n" +
                "Tôi cố gắng để kiểm tra xem cửa sổ pop-up bằng cách chỉ về cô ấy trong trường hợp này .\n" +
                "\n" +
                "Chủng tộc : Seraphim\n" +
                "\n" +
                "Tên : Mikoto\n" +
                "\n" +
                "Người sở hữu : Hiyuki\n" +
                "\n" +
                "HP : 27,960,000\n" +
                "\n" +
                "MP : 36,268,000\n" +
                "\n" +
                "▼\n" +
                "\n" +
                "…Tốt lắm , mức khả năng này không là cái gì để cười nhạo . Nhân tiện nếu tôi nhấn vào biểu tượng “▼” , nhiều chi tiết hơn về tình trạng hoặc kĩ năng sẽ được hiển thị , nhưng tôi đã bỏ qua vì nó chỉ là hàng chục dòng tầm thường khác.\n" +
                "\n" +
                "Ý tôi là . Tôi biết mức khả năng level của Tengai sớm điều đó thật lố bịch , nhưng đối với Mikoto nó đã quá điên rồ .\n" +
                "\n" +
                "Cô ấy là SR phần thưởng  class  đầu tiên ( Siêu hiếm ) mà tôi nhấn vào Gacha , do đó cô ta được dùng làm như là pet lâu hơn Tengai . Mức độ leve đạt đặt tới mức tối đa , tương tự như Tengai cách đây đã lâu , nhưng mức khả năng cô ấy gần tương tự như tôi , chỉ sổ không được lên cao như nầy .\n" +
                "\n" +
                "Nói chung là nếu khả năng pet là cao đáng kể hơn so với chủ nhân , nó không hợp lý gì cả trong trò chơi . Do đó , kể cả Kim Long [Naga Raja] , có mực level khả năng đã bị giảm đáng kể khi nó trở thành một giản thưởng Gacha . Cho dù nó đã trở thah2 một nửa item thời trang nửa vời , mức độ mà Kim Long [Naga Raja] ban đầu xuất hiện như boss của một sự kiện , lấy một nhóm 1500 người max level đã chuyển class lần 3 bao gồm cả tôi . Thậm chí khi đó chúng tôi phải mất hơn 2 tiếng đầu hồ để cùng nhau chiến đấu và đánh bại nó trong khi đang bị lag . Không phải là level của nó ngang bằng tương tự trở lại như hồi đó rồi ?\n" +
                "\n" +
                "Hơn nữa, seraphim là một chủng tộc thiên thần thuộc hàng top . Mặc dù nó không xuất hiện như một MOB , khi tôi cảm thấy nó trông ra sao ngay lúc này , kể từ khi MOB thiên thần 2 cánh trên đỉnh của “ Ngọn tháp sa ngã “ level là 40 – 50 lần cao hơn so với những gì đã triển khai trong ngọn tháp ấy . Có thể nếu seraphim triển như một MOB , nó sẽ trở thành một cái gì đó như thế này ? Có thể đoán trước bởi những con số đó .\n" +
                "\n" +
                "Tôi không biết lý do gì , nhưng mà giới hạn pet mà đã được xác lập đã biến mất . dù sao đi chăng nữa nếu tình tình cứ như vậy . Tôi sẽ bị giết ngay lập tức nếu họ chống lại tôi …\n" +
                "\n" +
                "…Vậy thì , tôi cần phải cư xử cho cẩn thận để không xúc phạm tới họ .\n" +
                "\n" +
                " “ Xin lỗi , cả hai người ; tôi có một chút chuyện muốn hỏi …”\n" +
                "\n" +
                "Ngay sau khi tôi vừa mới nói , cả gã điển trai và người phụ nữ đứng lên cùng một lúc và nét mặt họ trở nên choáng váng như thể đang bị sét đánh\n" +
                "\n" +
                "Chuyện này … Chuyện này có vẻ tệ ! Tôi làm chuyện gì sai à …? Tôi phải làm gì bây giờ …?\n" +
                "\n" +
                "“Công chúa ! Với những kẻ như chúng thần , chúng thần không xứng đáng để có những lời lẽ như vậy .”\n" +
                "\n" +
                "“Công chúa , có lẽ người đã mất trí nhớ vì người đã ngủ một một giấc ngủ vĩnh hằng ?”\n" +
                "\n" +
                "“Hả ?! Ah- không phải đâu , tôi nhớ mà . Tengai-san va Mikoto-san , hai người đã giúp tôi rất như nhiều như DPS và hồi phục”\n" +
                "\n" +
                "Sau khi tôi đã nói như vậy . tôi có thể nhìn thấy được sự nhẹ nhõm trên khuôn mặt của họ.\n" +
                "\n" +
                "Hmm? Nhân tiện đây tôi tự hỏi nếu đây là một sự xuất hiện được công nhận phổ biến trò chơi thì…\n" +
                "\n" +
                "“Dẫu vậy , xin vui lòng bằng cách nào đó hãy ném bỏ đi kính ngữ khi gọi tên của chúng thần.”\n" +
                "\n" +
                "Mặc dù đang nói như vậy bởi Mikito người đang cúi chào tôi… ngay từ đâu không có nói chuyện với pet trong trò chơi !\n" +
                "\n" +
                "A , đợi đã . Chắc chắn là có . Khi tôi còn trong chiến đấu , giọng diễn viên lồng tiếng đã hét lên ‘Đi’ , ‘Chùn bước’ , ‘Loại bỏ nó!’ về phía pet …\n" +
                "\n" +
                "Nói cách khác , có lẽ những thứ như thế ? Sử dụng cảm giác cho ra một câu lệnh mà không có lòng khoan dung ??\n" +
                "\n" +
                "Khi tôi rụt rè nhìn vào họ , họ đang chờ đợi lời nói của tôi với đôi mắt lấp lánh kì lạ .Nếu họ là những con cún , họ sẽ ngồi trong khi đuôi vẫn vẫy .\n" +
                "\n" +
                "Tôi tự hỏi nếu tất cả đều ổn… nói chuyện ngạo mạng như thế thì . Có lẽ là một cái gì đó giống như một tham sổ ẩn mà họ có mà tôi không biết về chuyện này sẽ đi xuống và sau đó họ sẽ nổi loạn. Thành thật mà nói tôi nghĩ sẽ bị thổi bay đi chỉ trong 1 hit…\n" +
                "\n" +
                "Th-thế thì không còn cách nào khác . Thật là nguy hiểm nếu tôi làm cho họ phải chờ đợi lâu hơn nữa.\n" +
                "\n" +
                "Tôi đã nói chuyện một lần nữa với hại người họ trong khi cố gắng để duy trì phong trái của Hiyuki trong trò chơi .\n" +
                "\n" +
                "“Yea, cả hai người đã gặp rắc rối à . Làm thế nào mà mấy người có thể quản lý giấc ngủ của tôi yên bình mà không có bất cản trở gì . Tôi rất cảm kích điều đó .”\n" +
                "\n" +
                "“Vâng ạ , chúng thần rất biết ơn với hạnh phúc của người .”\n" +
                "\n" +
                "Ah- chuyện này có phần nào đó khả quan đây .\n" +
                "\n" +
                "“…Chuyện gì đã xảy ra trong khi tôi đã chìm sâu trong giắc ngủ của mình thế ?? Tôi muốn nghe tình hình .”\n" +
                "\n" +
                "Sau khi tôi đã nói vậy , Tengai sửa tự thế của mình từ vị trí quỳ gối và chỉ theo hướng cánh cửa .\n" +
                "\n" +
                "“ Vâng , về vấn đề ấy , trong khi đó là bất cẩn của thần , chúng thần sẽ mời các đối tượng của người vào phòng ngai vàng.”\n" +
                "\n" +
                "“ Khởi đầu từ chúng thần 4 Vua Quỷ Thiên Giới , 7 con thú của tai họa , và 13 Vị tướng quỷ , tất cả chúng thần đang háo hức chờ đợi cho sự trở về của người , Công chúa.”\n" +
                "\n" +
                "Mikoto vui vẻ thêm lòi nói . Mặc dù tôi trả lời họ với một cái gật , đầu tôi đầy ắp những cô hỏi .\n" +
                "\n" +
                "4 Vua Quỷ Thiên Giới ? 7 con thú của tai họa ?? 13 tướng lĩnh quỷ ???...Cái quái gì vậy ?\n" +
                "\n" +
                "Tengai duyên dáng phục vụ bàn tay của anh ấy . Tôi cảm thấy bàn tay tôi bị kéo vào anh ta và hộ tống tôi nhẹ nhàng từ trong quan tài như đối xử với một vật thể mong manh dễ vỡ . Khi tôi bước xuống sàn , Mikito tiếp cận và nhanh chóng lấy ra một chiếc lược và kéo léo bắt đầu chải mái tóc dài của tôi.\n" +
                "\n" +
                "“ Vậy thì công chúa , chúng ta sẽ ghé qua nơi đó chứ?”\n" +
                "\n" +
                "Nói như vậy , Tengai dẫn đường cho chúng tôi ra phòng trong khi Mikoto đi theo từ đằng sau để tôi rời khỏi căn phòng , đang bị nhét vô giữa của hai người đó . Tôi ra vẻ bình tĩnh nhưng bên trong , tôi đang run rẩy và có thể phá bĩnh chính bản thân mình . Với cảm giác dona dona của một con bò hoặc lợn được đưa vào lò mở , tôi bắt đầu đi bộ một cách mù quán vào hành lang rộng lớn đó .\n" +
                "\n" +
                "-------------------\n" +
                "\n" +
                "Splash splash splash --- Nước nóng được phun ra mạnh mẽ từ miệng của bức tượng sư tử tại bốn hướng.\n" +
                "\n" +
                "Làm thế nào chuyện đó lại đến mức này…?\n" +
                "\n" +
                "Trong đám mây hơi nước , ngồi xuống trên bậc đá cẩm thạch ở rìa bờ tắm , tôi ngập nước tới eo mình – phần dưới của tôi ngâm trong nước nóng theo kiểu tôi một mép bờ . Nào , để nói về bản thân … làn da trắng trẻo của tôi khá phức tạp ( Nó khá là khó khăn để điều chỉnh màu da này trong khoảng thời gian tạo nhân vật ) . Giống như yuki từ tên tôi , Hiyuki . Trong khi thả dòng suy nghĩ của mình trên hình dáng thon thả này , tôi nhận ra rằng tôi đã hỏi một câu hỏi cho bản thân mình vô số lần .\n" +
                "\n" +
                "Lúc ấy tôi nghĩ rằng sẽ đi thằng vào phòng ngay vàng , nhưng thay vì như vậy tôi đã được đưa vào phòng tắm .\n" +
                "\n" +
                "Lúc đầu tôi không thể hiểu được điều này . Chiều rộng , chiều dài hành lang , chi tiết của thiết kết ; chúng rất khác so với những gì ở bên trong trò chơi , mặc dù có giống nhau ở thiết kế . Dù sao đi chăng nữa . Tôi nghĩ ‘ kích thước tối đã là to gần bằng trường học ‘ , nhưng quy mô của lâu đài này tuyệt với tới mức nó làm Cung điện Versailles trông giống như một nhà chó và nó dễ dàng phù hợp với 23 khu .\n" +
                "\n" +
                "“ Đúng rồi , đã đến cần phải thư giãn bản thân mình sau một thời gian dài như vậy ?”\n" +
                "\n" +
                "Để che dấu tâm trí của tôi khỏi sự sợ hãi , tôi đi bộ về hướng bồn tắm ( Mặc dù tôi có gọi như vậy , lâu đài trắng  trắng làm tôi nghĩ “ đây là loại đền gì nhỉ ?” vì nó sử dụng nhiều loại đá cẩm thạch phong phú ) bởi riêng bản mình … đúng vậy , tôi cũng đã cố gắng để , nhưng cuối cùng sự kiên nhẫn của tôi đã tới giới hạn vì vậy tôi quay lại và hỏi một câu hỏi.\n" +
                "\n" +
                "“…Tại sao anh cũng đi cũng đi cùng vậy ?!.”\n" +
                "\n" +
                "Người đang được nói đến – hoặc chính xác hơn là rồng Tengai này , trong giây lát làm vẻ mặt hững hờ , hoàn toàn trở nên nude mà không có bị lúng túng , anh ta trả lời trung thực.\n" +
                "\n" +
                "“Dĩ nhiên , là để giúp người tắm , công chúa ?”\n" +
                "\n" +
                "“…” ( Trans : Thánh giả nai tỏ vẻ ngây thơ dụ gái well play =]] )\n" +
                "\n" +
                "thêm một tiếng sột soạt  đi sang một bên . Sau đó anh ta xuất hiện đứng bên cạnh Mikoto hoàn toàn nude , và khi tôi bước ra , anh ta đến bên để đi cùng với tôi\n" +
                "\n" +
                "Nhưng điều này không phải là điều bình thường phải không ?! Tôi vẫn hiểu nếu là một maid hoặc Mikoto đi theo tôi đến tắm . Tuy nhiên , bình thường khi một thiếu nữ ( không phải tôi , tôi đang nói về Hiyuki !) đi đến tắm , điều gì đó giống như một đứa nhóc , người đàn ông đi theo cô là không thể xảy ra phải không ?!\n" +
                "\n" +
                "…Vâng…vì một số lí do tôi hiểu được điều đó . Trong khi tôi đang bị tước đoạt , đã có\n" +
                "\n" +
                "Dù tôi không phải Agnes-san , tôi chắc chắn cô ấy sẽ nói ‘dừng lại’ ở một nơi nào đó !\n" +
                "\n" +
                "“…Không , tôi không cần anh . Ý tôi là , nó thật sự rất xấu hổ .”\n" +
                "\n" +
                "Sauk hi tôi thẳng thừng trả lời như thế , Tenga cười ầm lên.\n" +
                "\n" +
                "“Hahaha , giờ thì sao vào lúc khuya thế này ? Không phải là việc tắm với công chúa là một lễ nghi hằng ngày sao ?” ( Trans : vl lễ nghi hằng ngày , thằng này được chị thích  rồi :”> )\n" +
                "\n" +
                "Đó là lúc anh ta ở dạng rồng ! Hơn nữa nguyên nhân là do ngâm trong nước nóng ở đây trong 1 phút sẽ bonus cho 3% tự phục hồi hp , và 15% tất cả kháng nguyên tố trong 30 phút , và tôi chỉ cần mang theo pet với tôi trước khi đi chiến đấu .\n" +
                "\n" +
                "Tôi muốn gây ra một chút ầm ĩ bằng cách giậm chân hết mức có thể , nhưng bất kể điều gì chuyện đó thật đúng là vô nghĩa .\n" +
                "\n" +
                "Làm như thế nào bây giờ tôi có nên đặt lại nó , nó là môt sự tức giận đối với một hành động khiêm nhường .Bên cạnh đó , nếu anh ta giận tôi , tôi sẽ chết .\n" +
                "\n" +
                "“ Đến đây , đến đây ! , nói chuyện quá lâu trong phòng thay quần áo này sẽ làm cơ thể người lạnh đi ,xin hãy đi về phía phòng tắm .”\n" +
                "\n" +
                "Trong khi tự hỏi tôi sẽ làm gì , vai tôi đã được đẩy bởi Tenga . Khi tôi cảm thấy làn da của anh ấy trên vai của mình , toàn bộ cơ thể của tôi trở nên tê cứng… hay đúng hơn là hông của tôi trở nên lung lay . Còn lại như thế , toi đã được hộ tống về phía phòng tắm  .\n" +
                "\n" +
                "“ Thôi được rồi , thứ lỗi cho thần vì đã mạo phạm- “\n" +
                "\n" +
                "Tôi cuối cùng cũng ngồi xuống các bậc của phòng tắm  , chỉ duy nhất một người hiện tại là người đàn ông mang điều báo thô lỗ đang khỏa thân dưới nước nóng . Mặc dù tôi muốn tránh mặt nó càng nhiều càng tốt , nhưng cái kích cỡ gì thế này , nó là một chai bia ? Hơn nữa anh ta đang lắc một số chai đầy chứa đầy cái gì đó bằng cả hai tay , và nó đang sủi bọt …aah , nó là xà phòng .\n" +
                "\n" +
                "Kể từ khi một chiếc khăn tắm bằng xà phòng có thể làm tổn hại tới làn da của tôi ,  tắm rửa với lòng bản tay của anh ta là tốt nhât ~…\n" +
                "\n" +
                "Khi tôi lơ đãng thoát khỏi từ thực tế . thời điểm khi tay ta chặm vào cơ thể tôi\n" +
                "\n" +
                "“Aahyaa…!!”\n" +
                "\n" +
                "Mặc dù theo phản xạ giọng nói đáng yêu của tôi cất lên , Tengai chỉ cần quan tâm – tự do hỏi “ Có chỗ ngứa nào không , công chúa ?” với một khuôn mặt không biết gì ( Trans : nó xạo đấy , mặt nó mà không biết chị mày đi bằng đầu )\n" +
                "\n" +
                "Cảm giác từ đuôi tóc và đầu ngón chân tôi , bên trên lỗ rốn của tôi và lên đến cái bộ phần nội thất mà ngay cả tôi vẫn chưa check được nó , tất cả nó đã được nhào nặn kỹ lưỡng . Cuối cùng tôi cảm thấy như tôi vừa trở thành nạn nhân bị cưỡng hiếp . Một cách vô thức , cuối cùng tôi khóc nức nở bên trong phòng tắm. ( Trans : sướng chết mịe mà còn khóc , khóc khóc cái *beep*)\n" +
                "\n" +
                "Sau khi tôi rời khỏi phòng tắm tôi cảm thấy chóng mặt , Mikoto đã ở đó chờ tôi . Trong tay cô ấy , một chiếc váy tương tự  như các thiết kết từ trang phục trước đo , mặc dù vậy váy khá rộng . Cô ấy bắ tôi mặc nó . Cuối cùng , lần này chúng tôi dối mặt về phía phòng ngai vàng  .");
        mScrollView.setVerticalScrollBarEnabled(false);
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (max == 0) {
                    max = mScrollView.getChildAt(0).getHeight() - mScrollView.getHeight();
                    mProgressBar.setMax(max);
                }
                // if(System.currentTimeMillis()-mTimeCurrent>200){
                mProgressBar.setProgress(mScrollView.getScrollY());
                //}
            }
        });
        mImgShowSettingFragment = (ImageView) findViewById(R.id.imgShowSettingFragment);
        mImgShowSettingFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mThisFragment.setVisibility(View.VISIBLE);
                //fragment.setVisibility(View.VISIBLE);
            }
        });
        mImgBackFormReadBook = (ImageView)findViewById(R.id.imgBackFormReadBook);
        mImgBackFormReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onCloseFragmentSetting() {
        max = mScrollView.getChildAt(0).getHeight() - mScrollView.getHeight();
        mProgressBar.setMax(max);
        mThisFragment.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFontSizePlus() {
        textSize = textSize + 1;
        mTvReadBook.setTextSize(textSize);
        ((SettingFragment) fragment).setTextSizeS(String.valueOf(textSize));
    }

    @Override
    public void onFontSizeMinus() {
        textSize = textSize - 1;
        mTvReadBook.setTextSize(textSize);
        ((SettingFragment) fragment).setTextSizeS(String.valueOf(textSize));
    }

    @Override
    public void onMarginPlus() {
        lineSpacing = lineSpacing + 1;
        mTvReadBook.setLineSpacing(lineSpacing, 1);
        ((SettingFragment) fragment).setTextMarginS(String.valueOf(lineSpacing));
    }

    @Override
    public void onMarginMinus() {
        lineSpacing = lineSpacing - 1;
        mTvReadBook.setLineSpacing(lineSpacing, 1);
        ((SettingFragment) fragment).setTextMarginS(String.valueOf(lineSpacing));
    }

    @Override
    public void onNightMode() {
        mScrollView.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        mTvReadBook.setTextColor(getResources().getColor(R.color.colorDarkWhite));
    }

    @Override
    public void onNormalMode() {
        mScrollView.setBackgroundColor(getResources().getColor(R.color.colorDarkWhite));
        mTvReadBook.setTextColor(getResources().getColor(R.color.colorGrey));
    }

    @Override
    public void onChangeFont(int i) {
        mTypeFace = Typeface.createFromAsset(getAssets(), mFont[i]);
        mTvReadBook.setTypeface(mTypeFace);
    }
}
