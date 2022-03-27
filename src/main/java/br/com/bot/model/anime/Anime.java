package br.com.bot.model.anime;

public class Anime {
    private Long id;
    private Title title;
    private String bannerImage;
    private MediaCoverImage coverImage;
    private MediaFormat format;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public MediaCoverImage getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(MediaCoverImage coverImage) {
        this.coverImage = coverImage;
    }

    public MediaFormat getFormat() {
        return format;
    }

    public void setFormat(MediaFormat format) {
        this.format = format;
    }
}
